package com.shreyas.concurrency.concurrencyissues;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StarvingThreads {
    private static final Lock lock = new ReentrantLock(true);  // fair lets threads to have more even distribution of access to resource which will prevent starvation, at some extent

    public static void main(String[] args) {
        Callable<Boolean> thread = () -> {
            String name = Thread.currentThread().getName();
            int threadNum = Integer.parseInt(name.split("-")[3]);
            boolean keepGoing = true;
            while (keepGoing) {
                lock.lock();
                try {
                    System.out.println("Thread-" + threadNum + " acquired lock");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread-" + threadNum + " interrupted");
                    Thread.currentThread().interrupt();
                    return false;
                } finally {
                    lock.unlock();
                }
            }
            return true;
        };

        var executor = Executors.newFixedThreadPool(3);
        try {
            var futures = executor.invokeAll(
                    List.of(
                            thread,
                            thread,
                            thread
                    ),
                    10,
                    TimeUnit.SECONDS
            );
        } catch (RuntimeException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        executor.shutdownNow();

        // Output of above code is,
        // Thread which gets the resource 1st will be greedy and other two threads will starve.
        // fair lock enabled all will get chance to acquire lock
    }
}
