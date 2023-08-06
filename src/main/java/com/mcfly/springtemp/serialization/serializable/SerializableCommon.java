package com.mcfly.springtemp.serialization.serializable;

import com.mcfly.springtemp.serialization.serializable.data.NotSerializableAddress;
import com.mcfly.springtemp.serialization.serializable.data.SerializablePersonWithTransientAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SerializableCommon {

    private static final String FILE = "che.ser";
    private static final Logger logger = LoggerFactory.getLogger(SerializableCommon.class);

    public static void main(String... args) {
        serializePerson();
        deserializePerson();
    }

    private static void serializePerson() {
        final NotSerializableAddress address = new NotSerializableAddress("state", "city", 43, 44);
        final SerializablePersonWithTransientAddress person = new SerializablePersonWithTransientAddress("Che", 42, address);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
            objectOutputStream.writeObject(person);
            objectOutputStream.flush();
            logger.info("Person object was serialized: {}", person);
        } catch (IOException e) {
            logger.error("Exception", e);
        }
    }

    private static void deserializePerson() {
        final File file = new File(FILE);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            final SerializablePersonWithTransientAddress person = (SerializablePersonWithTransientAddress) objectInputStream.readObject();
            logger.info("Person object was deserialized: {}", person);
        } catch (ClassNotFoundException | IOException e) {
            logger.error("Exception", e);
        } finally {
            file.deleteOnExit();
        }
    }
}

