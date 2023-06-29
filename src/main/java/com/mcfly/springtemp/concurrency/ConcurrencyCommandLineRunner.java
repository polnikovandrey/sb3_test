package com.mcfly.springtemp.concurrency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConcurrencyCommandLineRunner implements CommandLineRunner {

    public static void main(String... args) {
        new ConcurrencyCommandLineRunner().run(args);
    }


    @Override
    public void run(String... args) {
        ProducerConsumer.startProducerConsumer();
    }
}

