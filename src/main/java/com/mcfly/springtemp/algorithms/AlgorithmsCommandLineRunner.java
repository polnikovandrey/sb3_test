package com.mcfly.springtemp.algorithms;

import com.mcfly.springtemp.algorithms.items.math.*;
import com.mcfly.springtemp.algorithms.items.sorting.BubbleSortArray;
import com.mcfly.springtemp.algorithms.items.sorting.InsertionSortArray;
import com.mcfly.springtemp.algorithms.items.sorting.SelectionSortArray;
import com.mcfly.springtemp.algorithms.items.string.*;
import com.mcfly.springtemp.algorithms.items.structure.LinkedListMiddleOnePass;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AlgorithmsCommandLineRunner implements CommandLineRunner {

    public static void main(String... args) {
        new AlgorithmsCommandLineRunner().run(args);
    }

    @Override
    public void run(String... args) {
        Arrays.asList(
                new CountEqualNumbersInList(),
                new AddBinaryStrings(),
                new FindIndexOfFirstSubstringOccurrence(),
                new ValidateParenthesesPairs(),
                new SelectionSortArray(),
                new InsertionSortArray(),
                new SumCyphers(),
                new CheckPalindromeString(),
                new FibonacciFactory(),
                new BubbleSortArray(),
                new FindSingleDuplicateWithinArray(),
                new LinkedListMiddleOnePass(),
                new ReverseIntegerCheckIntBounds(),
                new ZigzagConverter(),
                new LongestPalindromicSubstringFinder(),
                new TwoArraysMedianFinder(),
                new MaxDistinctSubstringLengthCalculator()
        ).forEach(BaseAlgorithm::performAndPrint);
    }
}
