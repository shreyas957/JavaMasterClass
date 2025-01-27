package com.shreyas.concurrency.managiningthreads;

import com.shreyas.concurrency.ThreadColour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class WorkStealingThreadPoolExample {
    public static void main(String[] args) {
        int numbersLength = 100_000;
        long[] numbers = new Random().longs(numbersLength, 1, numbersLength).toArray();
        long sum = Arrays.stream(numbers).sum();

//        ExecutorService threadPool = Executors.newWorkStealingPool(4); // Generally set to equal or less than number of CPUs
//        ForkJoinPool threadPool = (ForkJoinPool) Executors.newWorkStealingPool(4);
        ForkJoinPool threadPool = ForkJoinPool.commonPool();
        List<Callable<Long>> task1 = new ArrayList<>();
        int taskNo = 10;
        int splitSize = numbersLength / taskNo;
        for (int i = 0; i < taskNo; i++) {
            int start = i * splitSize;
            int end = (i + 1) * splitSize;
            task1.add(() -> {
                return Arrays.stream(numbers, start, end).sum();
            });
        }
        long taskSum = 0;
        List<Future<Long>> futures = threadPool.invokeAll(task1);
        taskSum = futures.stream().mapToLong(f -> {
            try {
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e); // Wrap checked exceptions in an unchecked exception
            }
        }).sum();

        System.out.println("Sum: " + sum);
        System.out.println("Task Sum: " + taskSum);

        System.out.println("Parallelism: " + threadPool.getParallelism());

        System.out.println("CPUs: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Pool Size: " + threadPool.getPoolSize());
        System.out.println("Steal Count: " + threadPool.getStealCount());

        System.out.println(threadPool.getClass().getName());


        System.out.println(ThreadColour.ANSI_RED.color() + "***************************** Below is RecursiveTask Example ***************************** ");

        RecursiveTask<Long> task2 = new RecursiveSumTask(numbers, 0, numbers.length, 10);
        long result = threadPool.invoke(task2);
        System.out.println("Recursive Task Sum: " + result);

        threadPool.shutdown();
    }
}

class RecursiveSumTask extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    private final int division;

    RecursiveSumTask(long[] numbers, int start, int end, int division) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.division = division;
    }

    /**
     * Computes the sum of a segment of the array.
     * If the segment size is less than or equal to the division threshold, it sums the segment directly.
     * Otherwise, it splits the segment into two halves and recursively computes the sum of each half.
     * <p>
     * The `fork` method asynchronously executes the given task in the same pool as the current task.
     * The `join` method waits for the completion of the task and retrieves its result.
     *
     * @return the sum of the segment
     */
    @Override
    protected Long compute() {
        if ((end - start) <= (numbers.length / division)) {
            System.out.println("Start: " + start + " End: " + end);
            return Arrays.stream(numbers, start, end).sum();
        } else {
            int mid = start + (end - start) / 2;
            RecursiveSumTask leftTask = new RecursiveSumTask(numbers, start, mid, division);
            RecursiveSumTask rightTask = new RecursiveSumTask(numbers, mid, end, division);
            leftTask.fork();  // fork() means --> execute task in same pool current task is running
            rightTask.fork();
            return leftTask.join() + rightTask.join();  // join() means --> wait for task to complete and get result just like Future.get()
        }
    }
}
