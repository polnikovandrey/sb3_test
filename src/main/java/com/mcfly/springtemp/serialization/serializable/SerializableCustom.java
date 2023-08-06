package com.mcfly.springtemp.serialization.serializable;

import com.mcfly.springtemp.serialization.serializable.data.NotSerializableAddress;
import com.mcfly.springtemp.serialization.serializable.data.SerializablePersonWithCustomAddressSerialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SerializableCustom {

    private static final String FILE = "che.ser";
    private static final Logger logger = LoggerFactory.getLogger(SerializableCommon.class);

    public static void main(String... args) {
        serializePerson();
        deserializePerson();
    }

    private static void serializePerson() {
        final NotSerializableAddress address = new NotSerializableAddress("state", "city", 43, 44);
        final SerializablePersonWithCustomAddressSerialization person = new SerializablePersonWithCustomAddressSerialization("Che", 42, address);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FILE)))) {
            objectOutputStream.writeObject(person);
            logger.info("Person object was serialized: {}", person);
        } catch (IOException e) {
            logger.error("Exception", e);
        }
    }

    private static void deserializePerson() {
        final File file = new File(FILE);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            final SerializablePersonWithCustomAddressSerialization person = (SerializablePersonWithCustomAddressSerialization) objectInputStream.readObject();
            logger.info("Person object was deserialized: {}", person);
        } catch (ClassNotFoundException | IOException e) {
            logger.error("Exception", e);
        } finally {
            file.deleteOnExit();
        }
    }
}
