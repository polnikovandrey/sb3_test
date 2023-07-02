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
//                new MaxDistinctSubstringLengthCalculator().perform(),
//                new TwoArraysMedianFinder().perform(),
//                new LongestPalindromicSubstringFinder().perform(),
                new ZigzagConverter().perform()
        ).forEach(result -> System.out.println("$$$ " + result.toString()));
    }
}
