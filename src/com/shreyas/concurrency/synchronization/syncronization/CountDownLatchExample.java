package com.shreyas.concurrency.synchronization.syncronization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// Waiting for a Pool of Threads to Complete
class Worker implements Runnable {
    private List<String> outputScraper;
    private CountDownLatch countDownLatch;

    public Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doSomeWork();
        outputScraper.add("Counted down");
        countDownLatch.countDown();
    }

    private void doSomeWork() {
        try {
            System.out.println("Doing some work");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

// A Pool of Threads Waiting to Begin
// Problem : If we took the previous example, but this time started thousands of threads instead of five,
// it’s likely that many of the earlier ones will have finished processing before we have even called start() on the later ones.
// This could make it difficult to try and reproduce a concurrency problem, as we wouldn’t be able to get all our threads to run in parallel.
// To get around this, let’s get the CountdownLatch to work differently than in the previous example.
// Instead of blocking a parent thread until some child threads have finished, we can block each child thread until all the others have started.
class WaitingWorker implements Runnable {

    private List<String> outputScraper;
    private CountDownLatch readyThreadCounter;
    private CountDownLatch callingThreadBlocker;
    private CountDownLatch completedThreadCounter;

    public WaitingWorker(
            List<String> outputScraper,
            CountDownLatch readyThreadCounter,
            CountDownLatch callingThreadBlocker,
            CountDownLatch completedThreadCounter) {

        this.outputScraper = outputScraper;
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadBlocker = callingThreadBlocker;
        this.completedThreadCounter = completedThreadCounter;
    }

    @Override
    public void run() {
        readyThreadCounter.countDown();
        try {
            callingThreadBlocker.await();
            // do some work
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            outputScraper.add("Counted down");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            completedThreadCounter.countDown();
        }
    }
}


public class CountDownLatchExample {
    public static void main(String[] args) {
//        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
//        CountDownLatch countDownLatch = new CountDownLatch(5);
//        List<Thread> workers = Stream
//                .generate(() -> new Thread(new Worker(outputScraper, countDownLatch)))
//                .limit(5)
//                .toList();
//
//        workers.forEach(Thread::start);
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("***********");
//        outputScraper.add("Latch released");
//        outputScraper.forEach(System.out::println);


        // Example two  waitingWorker
        List<String> outputScraper1 = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch readyThreadCounter = new CountDownLatch(5);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(5);
        List<Thread> workers1 = Stream
                .generate(() -> new Thread(new WaitingWorker(
                        outputScraper1, readyThreadCounter, callingThreadBlocker, completedThreadCounter)))
                .limit(5)
                .toList();

        workers1.forEach(Thread::start);

        try {
            readyThreadCounter.await();
            outputScraper1.add("Workers ready");
            callingThreadBlocker.countDown();
            completedThreadCounter.await();
            outputScraper1.add("Workers complete");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("***********");
        System.out.println(outputScraper1);
    }
}
