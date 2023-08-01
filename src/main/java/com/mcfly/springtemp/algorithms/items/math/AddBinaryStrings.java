package com.mcfly.springtemp.algorithms.items.math;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;
import com.mcfly.springtemp.algorithms.items.StringArray;

public class AddBinaryStrings extends BaseAlgorithm<StringArray> {

    @Override
    public StringArray[] getArguments() {
        return new StringArray[] {
                new StringArray(new String[] { "101011", "10111"})
        };
    }

    @Override
    public Object calculate(StringArray stringArray) {
        final String a = stringArray.getStrings()[0];
        final String b = stringArray.getStrings()[1];
        final String longest = a.length() > b.length() ? a : b;
        final String other = a.length() > b.length() ? b : a;
        final int lengthDiff = longest.length() - other.length();
        final StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = longest.length() - 1; i > -1; i--) {
            int sum = carry;
            sum += longest.charAt(i) - '0';     // - '0' is used to convert char -> int
            sum += (i - lengthDiff < 0 ? 0 : (other.charAt(i - lengthDiff) - '0'));
            carry = sum > 1 ? 1 : 0;
            sb.insert(0, sum % 2 == 0 ? "0" : "1");
        }
        if (carry != 0) {
            sb.insert(0, "1");
        }
        return sb.toString();
    }
}
