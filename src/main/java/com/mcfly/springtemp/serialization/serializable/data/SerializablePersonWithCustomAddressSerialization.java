package com.mcfly.springtemp.serialization.serializable.data;

import java.io.*;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class SerializablePersonWithCustomAddressSerialization implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private transient NotSerializableAddress address;

    public SerializablePersonWithCustomAddressSerialization() {
    }

    public SerializablePersonWithCustomAddressSerialization(String name, int age, NotSerializableAddress address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Serial
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(address.getState());
        objectOutputStream.writeUTF(address.getCity());
        objectOutputStream.writeInt(address.getNumber());
        objectOutputStream.writeInt(address.getZipCode());
    }

    @Serial
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        final String state = objectInputStream.readUTF();
        final String city = objectInputStream.readUTF();
        final int number = objectInputStream.readInt();
        final int zipCode = objectInputStream.readInt();
        this.address = new NotSerializableAddress(state, city, number, zipCode);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(NotSerializableAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", address=" + address +
               '}';
    }
}
