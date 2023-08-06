package com.mcfly.springtemp.serialization.serializable.data;

import java.io.Serial;
import java.io.Serializable;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class SerializablePersonWithTransientAddress implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private transient NotSerializableAddress address;

    public SerializablePersonWithTransientAddress() {
    }

    public SerializablePersonWithTransientAddress(String name, int age, NotSerializableAddress address) {
        this.name = name;
        this.age = age;
        this.address = address;
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
