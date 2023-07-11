package com.mcfly.springtemp.algorithms.items.string;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;

/**
 * Найти максимальную подстроку-палиндром.
 * Подсказка:
 * На каждой итерации проверяем относительно соответствующего символа наличие палиндрома одного
 * из видов (четного/нечетного): вызываются 2 операции проверки.
 */
public class LongestPalindromicSubstringFinder extends BaseAlgorithm<String> {

    @Override
    public String[] getArguments() {
        return new String[] {
                "babad",
                "cbbd"
        };
    }

    @Override
    public Object calculate(String s) {
        if (s.length() == 1) {
            return s;
        }
        return new PalindromeExpander().calculate(s);
    }

    private static final class PalindromeExpander {

        private int lo = 0;
        private int max = 0;

        private String calculate(String s) {
            final char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                expandPalindrome(chars, i, i);
                expandPalindrome(chars, i, i + 1);
            }
            return s.substring(lo, lo + max);
        }

        private void expandPalindrome(char[] chars, int i, int j) {
            while(i >= 0 && j < chars.length && chars[i] == chars[j]) {
                i--;
                j++;
            }
            if (j - i - 1 > max) {
                max = j - i - 1;
                lo = i + 1;
            }
        }
    }
}
