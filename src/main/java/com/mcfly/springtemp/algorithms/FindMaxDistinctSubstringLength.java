package com.mcfly.springtemp.algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Для аргумента-строки найти максимальную длину подстроки без повторяющихся символов.
 */
public final class FindMaxDistinctSubstringLength {

    private static final String[] ARGUMENTS = {
            "01210456789"
    };

    public Object[] perform() {
        return perform(ARGUMENTS);
    }

    public Object[] perform(String[] inputs) {
        return Arrays.stream(inputs).map(input -> calculate(input)).toArray();
    }

    private Integer calculate(String input) {
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
