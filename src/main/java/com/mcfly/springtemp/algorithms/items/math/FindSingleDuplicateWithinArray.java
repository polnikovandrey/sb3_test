package com.mcfly.springtemp.algorithms.items.math;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;
import com.mcfly.springtemp.algorithms.IntegerArray;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Найти единственный дубликат в массиве, содержащем сплошные значения (1..50 или 1..100).
 * Подсказка:
 * В массиве будет один "лишний" элемент, т.е. длина равна длине массива 1..N + 1
 * Нужно вернуть: (сумма всех элементов) - (ожидаемая сумма 1..100)
 */
public class FindSingleDuplicateWithinArray extends BaseAlgorithm<IntegerArray> {

    @Override
    public IntegerArray[] getArguments() {
        final List<Integer> zeroToFifteenList = IntStream.rangeClosed(1, 50)
                .boxed()
                .collect(Collectors.toList());
        final List<Integer> zeroToHundredList = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());
        zeroToFifteenList.add(28, 14);
        zeroToHundredList.add(42, 99);
        return new IntegerArray[] {
                new IntegerArray(zeroToFifteenList.toArray(Integer[]::new)),
                new IntegerArray(zeroToHundredList.toArray(Integer[]::new))
        };
    }

    @Override
    public Object calculate(IntegerArray integerArray) {
        final int[] array = integerArray.toIntArray();
        int awaitedSum = 0;
        int actualSum = 0;
        for (int i = 0; i < array.length; i++) {
            actualSum += array[i];
            if (i + 1 < array.length) {
                awaitedSum += (i + 1);
            }
        }
        return actualSum - awaitedSum;
    }
}
