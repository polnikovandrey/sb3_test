package com.mcfly.springtemp.concurrency;

import java.util.LinkedList;
import java.util.List;

final class ProducerConsumer<T> {

    private static final int MAX_BUFFER_SIZE = 42;

    private final List<T> buffer;

    ProducerConsumer() {
        this.buffer = new LinkedList<>();
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

    public boolean isEmpty() {
        return buffer.isEmpty();
    }
}
