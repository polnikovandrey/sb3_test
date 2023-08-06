package com.mcfly.springtemp.serialization.serializable_common;

import java.io.Serial;
import java.io.Serializable;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private transient Object transientNonSerializable;

    public Person() {
    }

    public Person(String name, int age, Object transientNonSerializable) {
        this.name = name;
        this.age = age;
        this.transientNonSerializable = transientNonSerializable;
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

    public Object getTransientNonSerializable() {
        return transientNonSerializable;
    }

    public void setTransientNonSerializable(Object transientNonSerializable) {
        this.transientNonSerializable = transientNonSerializable;
    }

    @Override
    public String toString() {
        return "Person{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", nonSerializable=" + transientNonSerializable +
               '}';
    }
}
