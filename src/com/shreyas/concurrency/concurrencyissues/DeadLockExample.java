package com.shreyas.concurrency.concurrencyissues;

import java.io.File;

public class DeadLockExample {
    public static void main(String[] args) {
        // Deadlock example -> threads waiting on each other to release resource/lock
        File resourceA = new File("resourceA.csv");
        File resourceB = new File("resourceB.json");

        // Read from resourceA and dump into resourceB
//        Thread threadA = new Thread(() -> {
//            String threadName = Thread.currentThread().getName();
//            System.out.println(threadName + " is trying to acquire lock on resourceA");
//            synchronized (resourceA) {
//                System.out.println(threadName + " acquired lock on resourceA");
//                try {
//                    Thread.sleep(1000);  // Some kind of work
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println(threadName + " is trying to acquire lock on resourceB still holding lock on resourceA");
//                synchronized (resourceB) {
//                    System.out.println(threadName + " acquired lock on resourceB");
//                }
//                System.out.println(threadName + "has released lock on resourceB");
//            }
//            System.out.println(threadName + "has released lock on resourceA");
//        }, "THREAD-A");
//
//        Thread threadB = new Thread(() -> {
//            String threadName = Thread.currentThread().getName();
//            System.out.println(threadName + " is trying to acquire lock on resourceB");
//            synchronized (resourceB) {
//                System.out.println(threadName + " acquired lock on resourceB");
//                try {
//                    Thread.sleep(1000);  // Some kind of work
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(threadName + " is trying to acquire lock on resourceA still holding lock on resourceB");
//                synchronized (resourceA) {
//                    System.out.println(threadName + " acquired lock on resourceA");
//                }
//                System.out.println(threadName + "has released lock on resourceA");
//            }
//            System.out.println(threadName + "has released lock on resourceB");
//        }, "THREAD-B");


        // Organize locks in same order
        Thread threadA = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is trying to acquire lock on resourceA");
            synchronized (resourceA) {
                System.out.println(threadName + " acquired lock on resourceA");
                try {
                    Thread.sleep(1000);  // Some kind of work
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(threadName + " is trying to acquire lock on resourceB still holding lock on resourceA");
                synchronized (resourceB) {
                    System.out.println(threadName + " acquired lock on resourceB");
                }
                System.out.println(threadName + "has released lock on resourceB");
            }
            System.out.println(threadName + "has released lock on resourceA");
        }, "THREAD-A");

        Thread threadB = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " is trying to acquire lock on resourceA");
            synchronized (resourceA) {
                System.out.println(threadName + " acquired lock on resourceA");
                try {
                    Thread.sleep(1000);  // Some kind of work
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(threadName + " is trying to acquire lock on resourceB still holding lock on resourceA");
                synchronized (resourceB) {
                    System.out.println(threadName + " acquired lock on resourceB");
                }
                System.out.println(threadName + "has released lock on resourceB");
            }
            System.out.println(threadName + "has released lock on resourceA");
        }, "THREAD-B");

        threadA.start();
        threadB.start();
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
