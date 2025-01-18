package com.shreyas.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadColoursExercise {
    public static void main(String[] args) {
        StopWatch stopWatchGreen = new StopWatch(TimeUnit.SECONDS);
        StopWatch stopWatchPurple = new StopWatch(TimeUnit.SECONDS);
        StopWatch stopWatchRed = new StopWatch(TimeUnit.SECONDS);

        // stopWatch object is shared across all threads, making it shared resource
        // and hence, it is not thread-safe.
        Thread green = new Thread(stopWatchGreen::countDown, ThreadColour.ANSI_GREEN.name());
        Thread purple = new Thread(() -> stopWatchPurple.countDown(15),
                ThreadColour.ANSI_PURPLE.name());
        Thread red = new Thread(stopWatchRed::countDown,
                ThreadColour.ANSI_RED.name());
        green.start();
        purple.start();
        red.start();
    }
}

class StopWatch {
    private TimeUnit timeUnit;

    public StopWatch(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void countDown() {
        countDown(5);
    }

    public void countDown(int unitCount) {
        String threadName = Thread.currentThread().getName();
        ThreadColour threadColour = ThreadColour.ANSI_RESET;
        try {
            threadColour = ThreadColour.valueOf(threadName);
        } catch (IllegalArgumentException e) {
            // ignore
        }

        String colour = threadColour.color();
        for (int i = unitCount; i > 0; i--) {
            try {
                timeUnit.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            System.out.println(colour + threadName + " Thread : " + i);

        }
    }
}

