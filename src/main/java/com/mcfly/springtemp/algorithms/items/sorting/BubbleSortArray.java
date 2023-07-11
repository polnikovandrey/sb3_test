package com.mcfly.springtemp.algorithms.items.sorting;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;
import com.mcfly.springtemp.algorithms.IntArray;

import java.util.Arrays;

/**
 * Пузырьковая сортировка (большие значения "всплывают" в конец массива).
 * В цикле от начала до конца проходим массив и меняем рядом стоящие элементы если левый > правого.
 * Если была хоть одна замена - цикл повторяется.
 */
public class BubbleSortArray extends BaseAlgorithm<IntArray> {

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
