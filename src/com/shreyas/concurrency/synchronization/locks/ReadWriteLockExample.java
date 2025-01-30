package com.shreyas.concurrency.synchronization.locks;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteCounter {
    private int count = 0;
    private final ReadWriteLock lock;
    private final Lock readLock;
    private final Lock writeLock;

    public ReadWriteCounter() {
        lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    public int readCount() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public void incrementCount() {
        writeLock.lock();
        try {
            count++;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }
}

public class ReadWriteLockExample {
    public static void main(String[] args) {
        ReadWriteCounter counter = new ReadWriteCounter();
        Runnable readTAsk = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " is reading count: " + counter.readCount());
            }
        };

        Runnable writeTask = () -> {
            for (int i = 0; i < 10; i++) {
                counter.incrementCount();
                System.out.println(Thread.currentThread().getName() + " incremented count");
            }
        };

        var executor = Executors.newFixedThreadPool(3);
        executor.submit(writeTask);
        executor.submit(readTAsk);
        executor.submit(readTAsk);

        executor.shutdown();
    }
}
