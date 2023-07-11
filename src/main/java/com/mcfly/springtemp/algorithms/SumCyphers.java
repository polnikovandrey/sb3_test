package com.mcfly.springtemp.algorithms;

import java.util.Arrays;

/**
 * Преобразовать число в массив цифр.
 */
public class SumCyphers extends BaseAlgorithm<Integer> {

    @Override
    Integer[] getArguments() {
        return new Integer[] {
                12345,
                987654321
        };
    }

    @Override
    Object calculate(Integer integer) {
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
