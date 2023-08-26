package com.mcfly.springtemp.concurrency.synchronizers;

import java.time.LocalTime;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SemaphoreExample {

    // Одновременно будут работать максимум 5 worker-ов (всего 5 станков).
    public static final int PERMITS = 5;
    // Очередность FIFO не нужна.
    public static final boolean FAIR = false;
    // Количество рабочих, претендующих на работу на станке.
    public static final int WORKERS_COUNT = 50;

    public static void main(String... args) {
        new SemaphoreExample().execute();
    }

    private void execute() {
        final Semaphore semaphore = new Semaphore(PERMITS, FAIR);
        final Set<Worker> workers
                = IntStream.range(0, WORKERS_COUNT)
                           .mapToObj(i -> new Worker(semaphore, i))
                           .collect(Collectors.toSet());
        workers.forEach(Worker::work);
    }

    private record Worker(Semaphore semaphore, int idx) {

        private void work() {
                new Thread(() -> {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.printf("Seconds: %s. Worker #%s began working...%n", LocalTime.now().getSecond(), idx);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.printf("Seconds: %s. Worker #%s stopped working...%n", LocalTime.now().getSecond(), idx);
                    semaphore.release();
                }).start();
            }
        }
}
