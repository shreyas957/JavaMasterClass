package com.shreyas.concurrency;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * The CreatingThreads class demonstrates the creation and execution of threads in Java.
 * It creates and starts two threads: one using a custom thread class and another using a Runnable.
 */
public class CreatingThreads {
    /**
     * The main method is the entry point of the application.
     * It creates and starts two threads and also prints numbers in the main thread.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("""
                0 : Main Thread
                1 : CustomThread1 by extending Thread class
                2 : Thread with Runnable using lambda expression
                3 : CustomThread2 by implementing Runnable interface
                """);
        // Create and start a custom thread
        CustomThread1 customThread1 = new CustomThread1();
        customThread1.start();

        // Create a Runnable using a lambda expression
        Runnable runnable = () -> {
            for (int i = 0; i <= 8; i++) {
                System.out.println("2");
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Create and start a thread with the Runnable
        Thread thread1 = new Thread(runnable);
        thread1.start();

        Thread thread2 = new Thread(new CustomThread2());
        thread2.start();

        // Print numbers in the main thread
        for (int i = 0; i <= 3; i++) {
            System.out.println("0");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * The CustomThread class extends the Thread class and overrides the run method.
 * It prints numbers and sleeps for a specified time in each iteration.
 */
class CustomThread1 extends Thread {
    /**
     * The run method contains the code that is executed when the thread is started.
     * It prints numbers and sleeps for 1 second in each iteration.
     */
    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println("1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * We can create custom thread by implementing Runnable interface and overriding run method
 */
class CustomThread2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("3");
            try {
                TimeUnit.of(ChronoUnit.NANOS).sleep(10_000_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}