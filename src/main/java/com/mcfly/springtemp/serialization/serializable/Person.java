package com.mcfly.springtemp.serialization.serializable;

import java.io.Serial;
import java.io.Serializable;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private transient Address transientNotSerializable;

    public Person() {
    }

    public Person(String name, int age, Address transientNotSerializable) {
        this.name = name;
        this.age = age;
        this.transientNotSerializable = transientNotSerializable;
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

    public Object getTransientNotSerializable() {
        return transientNotSerializable;
    }

    public void setTransientNotSerializable(Address transientNotSerializable) {
        this.transientNotSerializable = transientNotSerializable;
    }

    @Override
    public String toString() {
        return "Person{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", nonSerializable=" + transientNotSerializable +
               '}';
    }
}
