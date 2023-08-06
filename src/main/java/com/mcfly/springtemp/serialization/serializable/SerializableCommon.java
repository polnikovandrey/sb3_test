package com.mcfly.springtemp.serialization.serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SerializableCommon {

    private static final Logger logger = LoggerFactory.getLogger(SerializableCommon.class);

    public static void main(String[] args) {
        serializePerson();
        deserializePerson();
    }

    private static void serializePerson() {
        final Address address = new Address("state", "city", 42, 43);
        final Person person = new Person("Che", 42, address);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("che.ser"))) {
            objectOutputStream.writeObject(person);
            logger.info("Person object was serialized: {}", person);
        } catch (IOException e) {
            logger.error("Exception", e);
        }
    }

    private static void deserializePerson() {
        final File file = new File("che.ser");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            final Person person = (Person) objectInputStream.readObject();
            logger.info("Person object was deserialized: {}", person);
        } catch (ClassNotFoundException | IOException e) {
            logger.error("Exception", e);
        } finally {
            file.deleteOnExit();
        }
    }
}

