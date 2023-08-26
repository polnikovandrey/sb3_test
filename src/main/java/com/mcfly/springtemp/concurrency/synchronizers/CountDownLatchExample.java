package com.mcfly.springtemp.concurrency.synchronizers;

import java.time.LocalTime;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class CountDownLatchExample {

    // Этот счетчик должен быть обнулен для того чтобы ждущие потоки запустились.
    private static final int COUNT = 8;

    public static void main(String... args) throws InterruptedException {
        new CountDownLatchExample().execute();
    }

    private void execute() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        final Set<Car> cars = IntStream.rangeClosed(1, 5).mapToObj(i -> new Car(countDownLatch, i)).collect(Collectors.toSet());
        cars.forEach(Car::waitForRacing);
        Thread.sleep(500);
        System.out.println("Ready!!!");
        countDownLatch.countDown();
        Thread.sleep(500);
        System.out.println("Steady!!!");
        countDownLatch.countDown();
        Thread.sleep(500);
        System.out.println("Go!!!");
        countDownLatch.countDown();
    }

    private record Car(CountDownLatch countDownLatch, int idx) {

        public void waitForRacing() {
                new Thread(() -> {
                    countDownLatch.countDown();
                    System.out.printf("Seconds: %s. Car #%s is at the start.%n", LocalTime.now().getSecond(), idx);
                    try {
                        Thread.sleep(50);
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.printf("Seconds: %s. Car #%s started to race.%n", LocalTime.now().getSecond(), idx);
                }).start();
            }
        }
}
