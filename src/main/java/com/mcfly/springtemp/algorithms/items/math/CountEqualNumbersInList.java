package com.mcfly.springtemp.algorithms.items.math;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;

import java.util.*;
import java.util.stream.Collectors;

public class CountEqualNumbersInList extends BaseAlgorithm<Integer[]> {

    public static void main(String... args) {
        new CountEqualNumbersInList().performAndPrint();
    }

    @Override
    public Integer[][] getArguments() {
        return new Integer[][] {
                new Integer[] { 1, 1, 2, null, 3, 3, null, 5, 4, 5, 6, null, 4, 7, 6 }
        };
    }

    @Override
    public Object calculate(Integer[] ints) {
        final List<Integer> list = Arrays.asList(ints);
        final int random = new Random().nextInt(4);
        if (random == 0) {
            final Map<Integer, Integer> result = new HashMap<>();
            list.stream()
                .filter(Objects::nonNull)
                .forEach(anInt -> {
                    final Integer value = result.get(anInt);
                    result.put(anInt, value == null ? 1 : value + 1);
                });
            return result;
        } else  if (random == 1) {
            final Map<Integer, Integer> result = new HashMap<>();
            list.stream()
                .filter(Objects::nonNull)
                .forEach(anInt -> result.merge(anInt, 1, Integer::sum));
            return result;
        } else if (random == 2) {
            final Map<Integer, Integer> result = new HashMap<>();
            list.stream()
                .filter(Objects::nonNull)
                .forEach(anInt -> result.compute(anInt, (key, value) -> value == null ? 1 : ++value));
            return result;
        } else {
            return list.stream()
                       .filter(Objects::nonNull)
                       .collect(Collectors.toMap(anInt -> anInt, anInt -> 1, (integer, integer2) -> {
                           System.out.printf("%s : %s%n", integer, integer2);
                           return integer + integer2;
                       }));
        }
    }
}
