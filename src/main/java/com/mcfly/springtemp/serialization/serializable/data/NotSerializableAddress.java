package com.mcfly.springtemp.serialization.serializable.data;

public final class NotSerializableAddress {

    private final String state;
    private final String city;
    private final int number;
    private final int zipCode;

    public NotSerializableAddress(String state, String city, int number, int zipCode) {
        this.state = state;
        this.city = city;
        this.number = number;
        this.zipCode = zipCode;
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
        return "Address{" +
               "state='" + state + '\'' +
               ", city='" + city + '\'' +
               ", number=" + number +
               ", zipCode=" + zipCode +
               '}';
    }
}
