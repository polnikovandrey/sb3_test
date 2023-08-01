package com.mcfly.springtemp.algorithms.items.string;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;
import com.mcfly.springtemp.algorithms.items.StringArray;

public class FindIndexOfFirstSubstringOccurrence extends BaseAlgorithm<StringArray> {


    @Override
    public StringArray[] getArguments() {
        return new StringArray[] {
                new StringArray(new String[] { "mississippi", "pi" }),
                new StringArray(new String[] { "mississippi", "issipi" }),
                new StringArray(new String[] { "mississippi", "issip" })
        };
    }

    @Override
    public Object calculate(StringArray stringArray) {
        final String haystack = stringArray.getStrings()[0];
        final String needle = stringArray.getStrings()[1];
        if (haystack.length() < needle.length()) {
            return -1;
        }
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (i + needle.length() > haystack.length()) {
                    break;
                } else {
                    for (int j = 0; j < needle.length(); j++) {
                        if (haystack.charAt(i + j) == needle.charAt(j)) {
                            if (j == needle.length() - 1) {
                                return i;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
