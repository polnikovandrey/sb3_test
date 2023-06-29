package com.mcfly.springtemp.concurrency;

final class DeadLock {

    final Object a = new Object();
    final Object b = new Object();

    public static void start() {
        new DeadLock().createDeadlock();

    }

    private void createDeadlock() {
        lockOnA();
        lockOnB();
    }

    private void lockOnA() {
        new Thread(() -> {
            synchronized (a) {
                try {
                    System.out.println("$$$ " + getClass().getSimpleName() + " lockOnA() thread sleep inside a.");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("$$$ " + getClass().getSimpleName() + " lockOnA() synchronized on b.");
                synchronized (b) {
                    System.err.println("$$$ " + getClass().getSimpleName() + " lockOnA() will never get inside b.");
                }
            }
        }).start();
    }

    private void lockOnB() {
        new Thread(() -> {
            synchronized (b) {
                try {
                    System.out.println("$$$ " + getClass().getSimpleName() + " lockOnB() thread sleep inside b.");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("$$$ " + getClass().getSimpleName() + " lockOnB() synchronized on a.");
                synchronized (a) {
                    System.err.println("$$$ " + getClass().getSimpleName() + " lockOnB() will never get inside a.");
                }
            }
        }).start();
    }
}
