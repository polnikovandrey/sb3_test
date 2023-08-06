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
        try (FileOutputStream fos = new FileOutputStream(FILE);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(person);
            oos.flush();
            logger.info("Person object was serialized: {}", person);
        } catch (IOException e) {
            logger.error("Exception", e);
        }
    }

    private static void deserializePerson() {
        final File file = new File(FILE);
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            final SerializablePersonWithTransientAddress person = (SerializablePersonWithTransientAddress) ois.readObject();
            logger.info("Person object was deserialized: {}", person);
        } catch (ClassNotFoundException | IOException e) {
            logger.error("Exception", e);
        } finally {
            file.deleteOnExit();
        }
    }
}

