package com.mcfly.springtemp.algorithms.items.sorting;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;
import com.mcfly.springtemp.algorithms.IntArray;

import java.util.Arrays;

public class InsertionSortArray extends BaseAlgorithm<IntArray> {

    @Override
    public IntArray[] getArguments() {
        return new IntArray[]{
                new IntArray(new Integer[]{32, 39, 21, 45, 23, 3}),
                new IntArray(new Integer[]{5, 3, 2, 1})
        };
    }

    @Override
    public Object calculate(IntArray intArray) {
        final int[] array = Arrays.stream(intArray.getIntegers()).mapToInt(Integer::intValue).toArray();

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
