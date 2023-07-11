package com.mcfly.springtemp.algorithms.items.string;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. *
 * An input string is valid if:
 *  Open brackets must be closed by the same type of brackets.
 *  Open brackets must be closed in the correct order.
 *  Every close bracket has a corresponding open bracket of the same type.
 *  <p></p>
 *  Tip: use stack to check correct order.
 */
public class ValidateParenthesesPairs extends BaseAlgorithm<String> {

    @Override
    public String[] getArguments() {
        return new String[] {
                "()",
                "()[]{}",
                "(]",
                "[{()}][()]"
        };
    }

    @Override
    public Object calculate(String s) {
        if (s == null || s.length() == 0 || s.length() % 2 != 0) {
            return false;
        }
        final LinkedList<Character> stack = new LinkedList<>();
        final Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            final Character awaited = map.get(ch);
            if (awaited == null) {
                stack.push(ch);
            } else if (awaited != stack.pop()) {
                return false;
            }
        }
        return stack.size() == 0;
    }
}
