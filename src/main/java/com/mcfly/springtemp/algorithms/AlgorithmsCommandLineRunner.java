package com.mcfly.springtemp.algorithms;

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
