package com.shreyas.concurrency.managiningthreads;

import com.shreyas.concurrency.ThreadColour;

import java.util.List;
import java.util.concurrent.*;

class ColourThreadFactory implements ThreadFactory {
    private String threadName;
    private int colorValue = 1;

    public ColourThreadFactory() {

    }

    public ColourThreadFactory(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = threadName;
        if (name == null) {
            name = ThreadColour.values()[colorValue].name();
        }
        if (++colorValue > (ThreadColour.values().length - 1)) {
            colorValue = 1;
        }
        thread.setName(name);
        return thread;
    }
}

public class ExecutorServiceExamples {
    public static void main(String[] args) {
        var multiExecutor = Executors.newCachedThreadPool();
        // setup list of callable tasks
        List<Callable<Integer>> taskList = List.of(
                () -> sum(1, 10, 1, "red"),
                () -> sum(10, 100, 10, "blue"),
                () -> sum(2, 20, 2, "green")
        );

//        try {
//            List<Future<Integer>> futures = multiExecutor.invokeAll(taskList);
//            for (var result : futures) {
//                System.out.println("Sum: " + result.get(500, TimeUnit.SECONDS));
//            }
//        } catch (InterruptedException | TimeoutException | ExecutionException e) {
//            throw new RuntimeException(e);
//        } finally {
//            multiExecutor.shutdown();
//        }

        try {
            Integer i = multiExecutor.invokeAny(taskList);
            System.out.println("Sum: " + i);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            multiExecutor.shutdown();
        }
    }

    public static void cachedmain(String[] args) {
        var multiExecutor = Executors.newCachedThreadPool();
        // We can use execute as well as submit. The difference is that execute does not return a Future object, whereas submit does.
        // Submit takes runnable as well as callable as arguments.
        try {
            Future<Integer> red = multiExecutor.submit(() -> sum(1, 10, 1, "red"));
            var blue = multiExecutor.submit(() -> sum(10, 100, 10, "blue"));
            var green = multiExecutor.submit(() -> sum(2, 20, 2, "green"));

            try {
                System.out.println("Red: " + red.get(500, TimeUnit.SECONDS));
                System.out.println("Blue: " + blue.get(500, TimeUnit.SECONDS));
                System.out.println("Green: " + green.get(500, TimeUnit.SECONDS));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            // Due to time delay, threads which are completed are used again for new tasks.
//            System.out.println("New tasks");
//            multiExecutor.execute(() -> sum(1, 10, 1, "black"));
//            multiExecutor.execute(() -> sum(10, 100, 10, "blue"));
//            multiExecutor.execute(() -> sum(2, 20, 2, "green"));

        } finally {
            multiExecutor.shutdown();
        }
    }

    public static void fixedmain(String[] args) {
        int count = 6;
        var multiExecutor = Executors.newFixedThreadPool(count, new ColourThreadFactory());
        for (int i = 0; i < count; i++) {
            multiExecutor.execute(ExecutorServiceExamples::countDown);
        }
        multiExecutor.shutdown();
    }

    public static void singlemain(String[] args) {
        // ExecutorService is an interface that represents an asynchronous execution mechanism which is capable of executing tasks in the background.
        // The threads created by it generally have name as pool-1-thread-1, pool-1-thread-2, etc.
        // By creating instance of ThreadFactory, we can override the standard way an executor creates a thread, and use custom functionality.

        var blueExecutor = Executors.newSingleThreadExecutor(new ColourThreadFactory(ThreadColour.ANSI_BLUE.name()));
        blueExecutor.execute(ExecutorServiceExamples::countDown);
        blueExecutor.shutdown();

        var isDone = false;
        try {
            isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (isDone) {
            System.out.println("Blue finished starting yellow");
            var yellowExecutor = Executors.newSingleThreadExecutor(new ColourThreadFactory(ThreadColour.ANSI_YELLOW.name()));
            yellowExecutor.execute(ExecutorServiceExamples::countDown);
            yellowExecutor.shutdown();
            try {
                isDone = yellowExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (isDone) {
                System.out.println("Yellow finished starting red");
                var redExecutor = Executors.newSingleThreadExecutor(new ColourThreadFactory(ThreadColour.ANSI_RED.name()));
                redExecutor.execute(ExecutorServiceExamples::countDown);
                redExecutor.shutdown();
                try {
                    isDone = redExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (isDone) {
                    System.out.println("All finished");
                }
            }
        }
    }

    public static void notmain(String[] args) {
        Thread blue = new Thread(ExecutorServiceExamples::countDown, ThreadColour.ANSI_BLUE.name());
        Thread yellow = new Thread(ExecutorServiceExamples::countDown, ThreadColour.ANSI_YELLOW.name());
        Thread red = new Thread(ExecutorServiceExamples::countDown, ThreadColour.ANSI_RED.name());
        blue.start();
        try {
            blue.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        yellow.start();
        try {
            yellow.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        red.start();
        try {
            red.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void countDown() {
        var threadName = Thread.currentThread().getName();
        var threadColour = ThreadColour.ANSI_RESET;
        try {
            threadColour = ThreadColour.valueOf(threadName.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Thread colour not found for thread: " + threadName);
        }

        String colour = threadColour.color();
        for (int i = 20; i >= 0; i--) {
            System.out.println(colour + " " + threadName.replace("ANSI_", "") + " " + i);

        }
    }

    private static int sum(int start, int end, int delta, String colorString) {
        var threadColor = ThreadColour.ANSI_RESET;
        try {
            threadColor = ThreadColour.valueOf("ANSI_" + colorString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Thread colour not found for thread: " + colorString);
        }

        String color = threadColor.color();
        int sum = 0;
        for (int i = sum; i <= end; i += delta) {
            sum += i;
        }
        System.out.println(color + Thread.currentThread().getName() + ", " + colorString + " " + sum);
        return sum;
    }
}
