package com.mcfly.springtemp.concurrency;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

final class ProducerConsumer<T> {

    private static final int MAX_BUFFER_SIZE = 42;

    private final List<T> buffer;

    ProducerConsumer() {
        this.buffer = Collections.synchronizedList(new LinkedList<>());
    }

    synchronized void produce(T value) throws InterruptedException {
        while (buffer.size() == MAX_BUFFER_SIZE) {
            System.out.println("$$$ " + getClass().getSimpleName() + " producer wait.");
            wait();
        }
        buffer.add(value);
        System.out.println("$$$ " + getClass().getSimpleName() + " produced: " + value.toString());
        notify();
    }

    synchronized T consume() throws InterruptedException {
        while (buffer.size() == 0) {
            System.out.println("$$$ " + getClass().getSimpleName() + " consumer wait.");
            wait();
        }
        final T result = buffer.remove(0);
        System.out.println("$$$ " + getClass().getSimpleName() + " consumed: " + result.toString());
        notify();
        return result;
    }

    boolean isEmpty() {
        return buffer.isEmpty();
    }




    static void startWithThreads() {
        final ProducerConsumer<Integer> producerConsumer = new ProducerConsumer<>();
        final AtomicInteger count = new AtomicInteger();
        final Thread consumerThread = new Thread(() -> {
            try {
                while (count.get() != 100 || !producerConsumer.isEmpty()) {
                    producerConsumer.consume();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Thread producerThread = new Thread(() -> {
            try {
                while(count.get() < 100) {
                    producerConsumer.produce(count.incrementAndGet());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumerThread.start();
        producerThread.start();
    }

    public static void startWithExecutors() {
        final ProducerConsumer<Integer> producerConsumer = new ProducerConsumer<>();
        final AtomicInteger count = new AtomicInteger();
        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            try {
                while (count.get() != 100 || !producerConsumer.isEmpty()) {
                    producerConsumer.consume();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
                while(count.get() < 100) {
                    producerConsumer.produce(count.incrementAndGet());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
