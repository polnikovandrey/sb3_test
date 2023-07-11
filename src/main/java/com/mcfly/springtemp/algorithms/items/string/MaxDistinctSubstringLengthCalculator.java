package com.mcfly.springtemp.algorithms.items.string;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Для аргумента-строки найти максимальную длину подстроки без повторяющихся символов.
 * <p/>
 * Подсказки:
 * Не нужно хранить подстроку, нужно хранить/сравнивать максимальный размер подстроки.
 * Буфер символов хранится в HashSet, наполняется итератором и частично чистится при обнаружении совпадения.
 * 2 указателя: итератор right и left - указатель на символ после первого символа с совпадением.
 * "abcdcba"
 * Нашли 2-е "c", удаляем из буфера символы строка[left] и инкрементим left пока не найдем первый совпадающий символ,
 * после чего еще разок инкрементим и удаляем.
 * В буфере должно остаться "dc", left = 3. Итерация продолжается
 */
public final class MaxDistinctSubstringLengthCalculator extends BaseAlgorithm<String> {

    @Override
    public String[] getArguments() {
        return new String[] {
                "abcdcba",
                "abcaqwertyq"
        };
    }

    @Override
    public Object calculate(String input) {
        int max = 0;
        int left = 0;
        Set<Character> set = new HashSet<>(input.length());
        for (int right = 0; right < input.length(); right++) {
            final char charAt = input.charAt(right);
            if (set.contains(charAt)) {
                while (charAt != input.charAt(left)) {
                    set.remove(input.charAt(left));
                    left++;
                }
                set.remove(input.charAt(left));
                left++;
            } else {
                max = Math.max(max, right - left + 1);
            }
            set.add(charAt);
        }
        return max;
    }
}
