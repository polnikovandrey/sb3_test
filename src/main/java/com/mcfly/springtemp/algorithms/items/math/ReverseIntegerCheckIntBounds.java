package com.mcfly.springtemp.algorithms.items.math;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;

/**
 * Обращение int.
 * Подсказки:
 * Результат должен накапливаться в long чтобы не вышел за пределы int. В конце нужно привести к int, но предварительно
 * проверить не будет ли переполнения (Integer.MIN_VALUE / Integer.MAX_VALUE).
 * int % 10 = значение последнего разряда
 * это значение нужно накапливать в long результате. На каждом шаге умножать результат на 10, после цикла делим на 10.
 * int / 10 вернет 0 если остался один разряд (проверка окончания while цикла)
 */
public class ReverseIntegerCheckIntBounds extends BaseAlgorithm<Integer> {

    @Override
    public Integer[] getArguments() {
        return new Integer[] {
                123,
                -123,
                120
        };
    }

    @Override
    public Object calculate(Integer x) {
        long result = 0L;
        while (x != 0) {
            int reminder = x % 10;
            result += reminder;
            result *= 10;
            x = x / 10;
        }
        result /= 10;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int)result;
    }
}
