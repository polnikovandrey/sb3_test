package com.mcfly.springtemp.io;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.stream.IntStream;

@Slf4j
public class FileInputOutputStreamCopyFileVariants {

    private static final String FILE_NAME = "che";

    public static void main(String... args) throws IOException {
        final File file = createTempFile();
        slowByteByByteFileCopy(file);
        fasterCustomBufferFileCopy(file);
        fastestBufferedFileCopy(file);
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

    private static void slowByteByByteFileCopy(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + ".copy")) {
            int aByte;
            while ((aByte = fis.read()) != -1) {
                fos.write(aByte);
            }
        } catch (IOException e) {
            log.error("Exception: ", e);
        } finally {
            final File copy = new File(file.getAbsolutePath() + ".copy");
            if (copy.exists() && copy.delete()) {
                log.info("Copy deleted successfully.");
            }
        }
    }

    private static void fasterCustomBufferFileCopy(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + ".copy")) {
            final byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            log.error("Exception: ", e);
        } finally {
            final File copy = new File(file.getAbsolutePath() + ".copy");
            if (copy.exists() && copy.delete()) {
                log.info("Copy deleted successfully.");
            }
        }
    }

    private static void fastestBufferedFileCopy(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + ".copy");
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            int aByte;
            while ((aByte = bis.read()) > -1) {
                bos.write(aByte);
            }
        } catch (IOException e) {
            log.error("Exception: ", e);
        } finally {
            final File copy = new File(file.getAbsolutePath() + ".copy");
            if (copy.exists() && copy.delete()) {
                log.info("Copy deleted successfully.");
            }
        }
    }
}
