package com.mcfly.springtemp.algorithms.items.math;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;

import java.util.Arrays;

public class FibonacciFactory extends BaseAlgorithm<Integer> {

    @Override
    public Integer[] getArguments() {
        return new Integer[] { 10, 20 };
    }

    @Override
    public Object calculate(Integer count) {
        int[] fibonacci = new int[count];
        fibonacci[0] = 1;
        fibonacci[1] = 1;
        for (int i = 2; i < count; i++) {
            fibonacci[i] = fibonacci[i - 2] + fibonacci[i - 1];
        }
        return Arrays.toString(fibonacci);
    }
}
