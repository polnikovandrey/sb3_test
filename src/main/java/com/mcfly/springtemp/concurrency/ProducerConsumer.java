package com.mcfly.springtemp.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class ProducerConsumer<T> {

    private static final int MAX_BUFFER_SIZE = 42;

    private final List<T> buffer;

    ProducerConsumer() {
        this.buffer = new LinkedList<>();
    }

    static void startProducerConsumer() {
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
}
