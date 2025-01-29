package com.shreyas.streams;

import java.util.Arrays;
import java.util.Random;

public class ParallelStreamsExample {
    public static void main(String[] args) {
        int numberLen = 100_000_000;
        long[] numbers = new Random().longs(numberLen, 1, numberLen).toArray();

        long startTime = System.nanoTime();
        double averageSerial = Arrays.stream(numbers).average().orElseThrow();
        long endTime = System.nanoTime() - startTime;
        System.out.println("Average Serial: " + averageSerial + " Time: " + endTime / 1_000_000.0 + "ms");

        startTime = System.nanoTime();
        double averageParallel = Arrays.stream(numbers).parallel().average().orElseThrow();
        endTime = System.nanoTime() - startTime;
        System.out.println("Average Parallel: " + averageParallel + " Time: " + endTime / 1_000_000.0 + "ms");


    }
}
