package com.mcfly.springtemp.algorithms.items.sorting;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;
import com.mcfly.springtemp.algorithms.IntegerArray;

import java.util.Arrays;

/**
 * В цикле i..n (i начитает с 0) на каждой итерации происходит поиск (выбор/select) индекса с минимальным значением.
 * Далее происходит взаимная замена значений в позиции i и в позиции минимального значения.
 * Time complexity O(N2)
 * Space complexity O(1)
 */
public class SelectionSortArray extends BaseAlgorithm<IntegerArray> {

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
        for (int i = 0; i < array.length; i++) {
            int minValueIdx = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minValueIdx]) {
                    minValueIdx = j;
                }
            }
            int temp = array[minValueIdx];
            array[minValueIdx] = array[i];
            array[i] = temp;
        }
        return Arrays.toString(array);
    }
}
