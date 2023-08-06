package com.mcfly.springtemp.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
public class ByteArrayInputOutputStreams {

    private static final String FILE_NAME = "che";

    public static void main(String... args) throws IOException {
        final File file = createTempFile();
        writeBytesToFile(file);
    }

    private static File createTempFile() throws IOException {
        final File file = File.createTempFile(FILE_NAME, null);
        file.deleteOnExit();
        try (PrintWriter pw = new PrintWriter(file)) {
            IntStream.range(0, 10).forEach(i -> pw.println("Che che che"));
            if (pw.checkError()) {
                throw new IOException("Temp file was not created.");
            }
        }
        return file;
    }

    private static void writeBytesToFile(File file) {
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
