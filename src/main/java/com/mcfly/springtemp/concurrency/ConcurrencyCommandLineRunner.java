package com.mcfly.springtemp.concurrency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ConcurrencyCommandLineRunner implements CommandLineRunner {

    public static void main(String... args) {
        new ConcurrencyCommandLineRunner().run(args);
    }


    @Override
    public void run(String... args) {
        startProducerConsumer();
    }

    private void startProducerConsumer() {
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
}

