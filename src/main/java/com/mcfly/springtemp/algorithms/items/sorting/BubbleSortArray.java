package com.mcfly.springtemp.algorithms.items.sorting;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;
import com.mcfly.springtemp.algorithms.IntegerArray;

import java.util.Arrays;

/**
 * Пузырьковая сортировка (большие значения "всплывают" в конец массива).
 * В цикле от начала до конца проходим массив и меняем рядом стоящие элементы если левый > правого.
 * Если была хоть одна замена - цикл повторяется.
 * Time complexity O(N2)
 * Space complexity O(1)
 */
public class BubbleSortArray extends BaseAlgorithm<IntegerArray> {

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
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    sorted = false;
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
        return Arrays.toString(array);
    }
}
