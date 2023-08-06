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
        try (FileOutputStream fos = new FileOutputStream(FILE);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutput oo = new ObjectOutputStream(bos)) {
            person.writeExternal(oo);
            oo.flush();
            logger.info("Externalized person: {}", person);
        } catch (IOException e) {
            logger.error("Exception", e);
        }
    }

    private static void deExternalizePerson() {
        final File file = new File(FILE);
        file.deleteOnExit();
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInput oi = new ObjectInputStream(bis)) {
            final NotSerializablePerson person = new NotSerializablePerson();
            person.readExternal(oi);
            logger.info("DeExternalized person: {}", person);
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Exception", e);
        }
    }
}
