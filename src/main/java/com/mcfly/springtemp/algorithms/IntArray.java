package com.mcfly.springtemp.algorithms;

import java.util.Arrays;

public final class IntArray {

    private final Integer[] integers;

    public IntArray(Integer[] integers) {
        this.integers = integers;
    }

    public Integer[] getIntegers() {
        return integers;
    }

    @Override
    public String toString() {
        return "IntArray{" +
                "integers=" + Arrays.toString(integers) +
                '}';
    }
}
