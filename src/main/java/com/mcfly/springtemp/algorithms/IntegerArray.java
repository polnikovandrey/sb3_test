package com.mcfly.springtemp.algorithms;

import java.util.Arrays;

public final class IntegerArray {

    private final Integer[] integers;

    public IntegerArray(Integer[] integers) {
        this.integers = integers;
    }

    public int[] toIntArray() {
        return Arrays.stream(integers).mapToInt(Integer::intValue).toArray();
    }

    @Override
    public String toString() {
        return "IntArray{" +
                "integers=" + Arrays.toString(integers) +
                '}';
    }
}
