package com.mcfly.springtemp.serialization.externalizable;

import com.mcfly.springtemp.serialization.externalizable.data.NotSerializableAddress;
import com.mcfly.springtemp.serialization.externalizable.data.NotSerializablePerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ExternalizableExample {

    private static final String FILE = "che.ext";
    private static final Logger logger = LoggerFactory.getLogger(ExternalizableExample.class);

    public static void main(String... args) {
        externalizePerson();
        deExternalizePerson();
    }

    private static void externalizePerson() {
        final NotSerializableAddress address = new NotSerializableAddress("state", "city", 43, 44);
        final NotSerializablePerson person = new NotSerializablePerson("che", 42, address);
        try (ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(FILE))) {
            person.writeExternal(objectOutput);
            objectOutput.flush();
            logger.info("Externalized person: {}", person);
        } catch (IOException e) {
            logger.error("Exception", e);
        }
    }

    private static void deExternalizePerson() {
        final File file = new File(FILE);
        file.deleteOnExit();
        try (ObjectInput objectInput = new ObjectInputStream(new FileInputStream(file))) {
            final NotSerializablePerson person = new NotSerializablePerson();
            person.readExternal(objectInput);
            logger.info("DeExternalized person: {}", person);
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Exception", e);
        }
    }
}
