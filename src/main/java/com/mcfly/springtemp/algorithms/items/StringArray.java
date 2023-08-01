package com.mcfly.springtemp.algorithms.items;

import java.util.Arrays;

public class StringArray {

    private String[] strings;

    public StringArray(String[] strings) {
        this.strings = strings;
    }

    public String[] getStrings() {
        return strings;
    }

    @Override
    public String toString() {
        return "StringArray{" +
               "strings=" + Arrays.toString(strings) +
               '}';
    }
}
