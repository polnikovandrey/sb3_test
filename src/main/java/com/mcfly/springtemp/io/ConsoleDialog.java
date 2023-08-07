package com.mcfly.springtemp.io;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleDialog {

    public static void main(String... args) {
        console();
        scanner();
        bufferedInputStreamReader();
    }

    private static void console() {
        final Console console = System.console();
        if (console == null) {
            System.err.println("System.console() unavailable. Please, use real shell to launch. IDE launch is not supported.");
            return;
        }
        System.out.println("Please, enter login:");
        final String login = console.readLine();
        System.out.println("=== Login echo: " + login);
        System.out.println("Please, enter password:");
        final char[] password = console.readPassword();
        System.out.println("=== Login echo: " + Arrays.toString(password));
        Arrays.fill(password, '0');
    }

    private static void scanner() {
        System.out.println("Please, enter some string:");
        final Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            final String stringInput = scanner.next();
            System.out.println("=== String input echo: " + stringInput);
        }
        System.out.println("Please, enter some int:");
        if (scanner.hasNextInt()) {
            final int intInput = scanner.nextInt();
            System.out.println("=== int input echo: " + intInput);
        }
    }

    private static void bufferedInputStreamReader() {
        System.out.println("Please, enter some data:");
        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(isr)) {
            final String input = br.readLine();
            System.out.println("=== Input echo: " + input);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
