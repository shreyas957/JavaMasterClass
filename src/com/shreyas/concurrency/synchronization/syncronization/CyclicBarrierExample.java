package com.shreyas.concurrency.synchronization.syncronization;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("All parties have arrived at the barrier"));

        Runnable runnable = () -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " is started working");
            try {
                Thread.sleep(2000);
                System.out.println("Thread " + Thread.currentThread().getName() + " is waiting on barrier");
                cyclicBarrier.await();   // it's like waiting on door
                System.out.println("Thread " + Thread.currentThread().getName() + " has crossed the barrier");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        var executorService = Executors.newFixedThreadPool(3);
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);


        System.out.println("Barrier parties: " + cyclicBarrier.getParties());
        cyclicBarrier.reset();


        executorService.shutdown();

    }

}
