package com.mcfly.springtemp.io;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class ByteArrayInputOutputStreams {

    public static void main(String... args) {
        writeAndReadBytes();
    }

    private static void writeAndReadBytes() {
        byte[] bytes = new byte[10];
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(10)) {
            baos.writeBytes(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 });
            bytes = baos.toByteArray();
            log.info("Successfully wrote byte array to ByteArrayOutputStream: {}", Arrays.toString(bytes));
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
            final byte[] bytesRead = bais.readAllBytes();
            log.info("Successfully read byte array from ByteArrayInputStream: {}", Arrays.toString(bytesRead));
        } catch (IOException e) {
            log.error("Exception: ", e);
        }
    }
}
