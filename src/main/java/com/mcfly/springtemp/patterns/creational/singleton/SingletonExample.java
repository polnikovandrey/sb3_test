package com.mcfly.springtemp.patterns.creational.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Slf4j
public class SingletonExample {

    public static void main(String... args) {
        createThreadSafeSingleton();
    }

    private static void createThreadSafeSingleton() {
        final Thread[] threads = new Thread[100];
        final AtomicInteger hashCodeStore = new AtomicInteger(-1);
        IntStream.range(0, threads.length)
                .forEach(i -> {
                    final Runnable runnable = () -> {
                        try {
                            Thread.sleep(100);
                            final ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
                            final int hashCode = singleton.hashCode();
                            synchronized (hashCodeStore) {
                                if (hashCodeStore.get() == -1) {
                                    hashCodeStore.addAndGet(hashCode);
                                } else if (hashCodeStore.get() == hashCode) {
                                    log.error("Singleton check failed");
                                }
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    };
                    threads[i] = new Thread(runnable);
                });
        Arrays.stream(threads).forEach(Thread::start);
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("Singleton check success");
    }
}
