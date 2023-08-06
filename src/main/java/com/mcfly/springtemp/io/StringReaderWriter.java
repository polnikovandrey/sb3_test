package com.mcfly.springtemp.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class StringReaderWriter {

    public static void main(String... args) {
        writeAndReadString();
    }

    private static void writeAndReadString() {
        final String str = "abcdefghij";
        String swString = "";
        try (StringWriter sw = new StringWriter(10);
             BufferedWriter bw = new BufferedWriter(sw)) {
            bw.write(str);
            bw.write(str);
            bw.write(str);
            bw.flush();
            log.info("Put string to StringWriter: {}", sw);
            swString = sw.toString();
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
        try (StringReader sr = new StringReader(swString);
             BufferedReader br = new BufferedReader(sr)) {
            final StringBuilder sb = new StringBuilder();
            final char[] buffer = new char[10];
            while (br.read(buffer) > -1) {
                sb.append(buffer);
            }
            log.info("Read string from StringReader: {}", sb);
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
    }
}
