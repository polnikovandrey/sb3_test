package com.mcfly.springtemp.io;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Slf4j
public class StringReaderWriter {

    public static void main(String... args) {
        writeAndReadString();
    }

    private static void writeAndReadString() {
        final String str = "abcdefghij";
        String swString = "";
        try (StringWriter sw = new StringWriter(10)) {
            sw.write(str);
            sw.write(str);
            sw.write(str);
            log.info("Put string to StringWriter: {}", sw);
            swString = sw.toString();
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
        try (StringReader sr = new StringReader(swString)) {
            final StringBuilder sb = new StringBuilder();
            final char[] buffer = new char[10];
            while (sr.read(buffer) > -1) {
                sb.append(buffer);
            }
            log.info("Read string from StringReader: {}", sb);
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
    }
}
