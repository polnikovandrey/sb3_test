package com.mcfly.springtemp.algorithms.items.sorting;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;
import com.mcfly.springtemp.algorithms.IntegerArray;

import java.util.Arrays;

public class InsertionSortArray extends BaseAlgorithm<IntegerArray> {

    @Override
    public IntegerArray[] getArguments() {
        return new IntegerArray[]{
                new IntegerArray(new Integer[]{32, 39, 21, 45, 23, 3}),
                new IntegerArray(new Integer[]{5, 3, 2, 1})
        };
    }

    @Override
    public Object calculate(IntegerArray integerArray) {
        final int[] array = integerArray.toIntArray();
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return Arrays.toString(array);
    }
}
