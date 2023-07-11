package com.mcfly.springtemp.algorithms.items.math;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;

import java.util.Arrays;

/**
 * Преобразовать число в массив цифр.
 */
public class SumCyphers extends BaseAlgorithm<Integer> {

    @Override
    public Integer[] getArguments() {
        return new Integer[] {
                12345,
                987654321
        };
    }

    @Override
    public Object calculate(Integer integer) {
        final int anInt = integer;

        int temp = anInt;
        int countDigits = 1;
        while ((temp = temp / 10) != 0) {
            countDigits++;
        }

        temp = anInt;
        int[] digits = new int[countDigits];
        for (int i = countDigits; i > 0; i--) {
            int reminder = temp % 10;
            temp = temp / 10;
            digits[i - 1] = reminder;
        }
        return Arrays.toString(digits);
    }
}
