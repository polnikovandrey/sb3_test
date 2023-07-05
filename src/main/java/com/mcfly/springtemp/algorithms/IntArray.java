package com.mcfly.springtemp.algorithms;

import java.util.Arrays;

final class IntArray {

    private final Integer[] integers;

    IntArray(Integer[] integers) {
        this.integers = integers;
    }

    Integer[] getIntegers() {
        return integers;
    }

    @Override
    public String toString() {
        return "IntArray{" +
                "integers=" + Arrays.toString(integers) +
                '}';
    }
}
