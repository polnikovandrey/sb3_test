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
                new FindIndexOfFirstSubstringOccurrence().perform(),
                new ValidateParenthesesPairs().perform(),
                new SelectionSortArray().perform(),
                new InsertionSortArray().perform(),
                new SumCyphers().perform(),
                new CheckPalindromeString().perform(),
                new FibonacciFactory().perform(),
                new BubbleSortArray().perform(),
                new FindSingleDuplicateWithinArray().perform(),
                new LinkedListMiddleOnePass().perform(),
                new ReverseIntegerCheckIntBounds().perform(),
                new ZigzagConverter().perform(),
                new LongestPalindromicSubstringFinder().perform(),
                new TwoArraysMedianFinder().perform(),
                new MaxDistinctSubstringLengthCalculator().perform()
        ).forEach(result -> System.out.println("$$$ " + result.toString()));
    }
}
