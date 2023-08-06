package com.mcfly.springtemp.serialization.externalizable.data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class NotSerializablePerson implements Externalizable {

    private String name;
    private int age;
    private NotSerializableAddress address;

    public NotSerializablePerson() {
    }

    public NotSerializablePerson(String name, int age, NotSerializableAddress address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(age);
        out.writeObject(address);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.age = in.readInt();
        this.address = (NotSerializableAddress) in.readObject();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public NotSerializableAddress getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "NotSerializablePerson{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", address=" + address +
               '}';
    }
}
