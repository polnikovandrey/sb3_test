package com.mcfly.springtemp.algorithms.items.math;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;
import com.mcfly.springtemp.algorithms.IntArray;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Найти единственный дубликат в массиве, содержащем сплошные значения (1..50 или 1..100).
 * Подсказка:
 * В массиве будет один "лишний" элемент, т.е. длина равна длине массива 1..N + 1
 * Нужно вернуть: (сумма всех элементов) - (ожидаемая сумма 1..100)
 */
public class FindSingleDuplicateWithinArray extends BaseAlgorithm<IntArray> {

    @Override
    public IntArray[] getArguments() {
        final List<Integer> zeroToFifteenList = IntStream.rangeClosed(1, 50)
                .boxed()
                .collect(Collectors.toList());
        final List<Integer> zeroToHundredList = IntStream.rangeClosed(1, 100)
                .boxed()
                .collect(Collectors.toList());
        zeroToFifteenList.add(28, 14);
        zeroToHundredList.add(42, 99);
        return new IntArray[] {
                new IntArray(zeroToFifteenList.toArray(Integer[]::new)),
                new IntArray(zeroToHundredList.toArray(Integer[]::new))
        };
    }

    @Override
    public Object calculate(IntArray intArray) {
        int awaitedSum = 0;
        int actualSum = 0;
        for (int i = 0; i < intArray.getIntegers().length; i++) {
            actualSum += intArray.getIntegers()[i];
            if (i + 1 < intArray.getIntegers().length) {
                awaitedSum += (i + 1);
            }
        }
        return actualSum - awaitedSum;
    }
}