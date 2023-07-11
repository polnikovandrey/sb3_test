package com.mcfly.springtemp.algorithms.items.string;

import com.mcfly.springtemp.algorithms.BaseAlgorithm;

/**
 * PAYPALISHIRING 3
 * P        A       H       N
 * A    P   L   S   I   I   G
 * Y        I       R
 */
public final class ZigzagConverter extends BaseAlgorithm<ZigzagConverter.Input> {

    public static class Input {
        String input;
        int numRows;

        private Input(String input, int numRows) {
            this.input = input;
            this.numRows = numRows;
        }

        @Override
        public String toString() {
            return "Input{" +
                    "input='" + input + '\'' +
                    ", numRows=" + numRows +
                    '}';
        }
    }

    @Override
    public Input[] getArguments() {
        return new Input[] {
                new Input("ABCDE", 4),
                new Input("PAYPALISHIRING", 3),
                new Input("PAYPALISHIRING", 4),
                new Input("A", 1)
        };
    }

    @Override
    public Object calculate(Input input) {
        return convertWithMatrix(input.input, input.numRows);
    }

    private String convertWithMatrix(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }
        final int stepSize = numRows + numRows - 2;
        final int stepCount = s.length() / stepSize + 1;
        final int colsPerStep = numRows - 1;
        final char[][] matrix = new char[numRows][stepCount * colsPerStep];
        for (int i = 0; i < s.length(); i++) {
            final int innerI = i % stepSize;
            int row;
            if (innerI < numRows) {
                row = innerI;
            } else {
                int diff = innerI - numRows + 2;
                row = numRows - diff;
            }


            int col;
            if (innerI < numRows) {
                col = i / stepSize * (numRows - 1);
            } else {
                int diff = innerI - numRows + 1;
                col = i / stepSize * (numRows - 1) + diff;
            }

            matrix[row][col] = s.charAt(i);
        }
        StringBuilder sb = new StringBuilder();
        for (final char[] inner : matrix) {
            for (char c : inner) {
                if (c != '\u0000') {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
