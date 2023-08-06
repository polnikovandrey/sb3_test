package com.mcfly.springtemp.serialization.externalizable.data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class NotSerializableAddress implements Externalizable {

    private String state;
    private String city;
    private int number;
    private int zipCode;

    public NotSerializableAddress() {
    }

    public NotSerializableAddress(String state, String city, int number, int zipCode) {
        this.state = state;
        this.city = city;
        this.number = number;
        this.zipCode = zipCode;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(state);
        out.writeUTF(city);
        out.writeInt(number);
        out.writeInt(zipCode);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        this.state = in.readUTF();
        this.city = in.readUTF();
        this.number = in.readInt();
        this.zipCode = in.readInt();
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public int getNumber() {
        return number;
    }

    public int getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "NotSerializableAddress{" +
               "state='" + state + '\'' +
               ", city='" + city + '\'' +
               ", number=" + number +
               ", zipCode=" + zipCode +
               '}';
    }
}
