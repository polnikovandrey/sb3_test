package com.mcfly.springtemp.algorithms;

public class ReverseIntegerCheckIntBounds extends BaseAlgorithm<Integer> {

    @Override
    Integer[] getArguments() {
        return new Integer[] {
                123,
                -123,
                120
        };
    }

    @Override
    Object calculate(Integer x) {
        long result = 0L;
        while (x != 0) {
            int rest = x % 10;
            result += rest;
            result *= 10;
            x = x / 10;
        }
        result /= 10;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int)result;
    }
}
