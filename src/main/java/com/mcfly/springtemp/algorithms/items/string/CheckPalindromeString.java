package com.mcfly.springtemp.algorithms.items.string;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;

public class CheckPalindromeString extends BaseAlgorithm<String> {

    @Override
    public String[] getArguments() {
        return new String[] {
                "zxcxz",
                "qwertytrewq",
                "zweztytrewq",
                "zwertytrewq",
                "qwertytrewz",
                "qwertytrewqz"

        };
    }

    @Override
    public Object calculate(String s) {
        return checkPalindromeRecursive(s);
    }

    private boolean checkPalindromeRecursive(String s) {
        if (s.length() == 1) {
            return true;
        } else if (s.substring(0, 1).equals(s.substring(s.length() - 1))) {
            if (s.length() == 2) {
                return true;
            }
            return checkPalindromeRecursive(s.substring(1, s.length() - 1));
        } else {
            return false;
        }
    }
}
