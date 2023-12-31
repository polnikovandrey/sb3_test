package com.mcfly.springtemp.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Arrays;

@Slf4j
public class CharArrayReaderWriter {

    public static void main(String... args) {
        writeBytesToFile();
    }

    private static void writeBytesToFile() {
        char[] chars = new char[10];
        try (CharArrayWriter caw = new CharArrayWriter(10);
             BufferedWriter bw = new BufferedWriter(caw)) {
            bw.write(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j' });
            bw.flush();
            chars = caw.toCharArray();
            log.info("Successfully wrote char array to CharArrayWriter: {}", Arrays.toString(chars));
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
        try (CharArrayReader car = new CharArrayReader(chars);
             BufferedReader br = new BufferedReader(car)) {
            final StringBuilder sb = new StringBuilder();
            int aChar;
            while ((aChar = br.read()) > -1) {
                sb.append((char)aChar);
            }
            log.info("Successfully read char array from CharArrayReader: {}", Arrays.toString(sb.toString().toCharArray()));
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
    }
}
