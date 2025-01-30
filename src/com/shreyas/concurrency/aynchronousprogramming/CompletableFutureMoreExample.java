package com.shreyas.concurrency.aynchronousprogramming;

import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureMoreExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        combinedFuture.get();   // or join()

        System.out.println("All futures are done? " + combinedFuture.isDone());
        System.out.println("future1: " + future1.isDone() + ", future2: " + future2.isDone() + ", future3: " + future3.isDone());

        String collect = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));
        System.out.println(collect);


        // Handling exceptions
        String name = null;
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation Error!");
            }
            return "Hello, " + name;
        }).handle((result, ex) -> {
            if (ex != null) { // means exception occurred
                System.out.println("Exception: " + ex.getMessage());
                return "Fallback Value"; // Recovery
            } else {
                return result + "Hi"; // Normal case
            }
        });
        System.out.println("Result: " + completableFuture.join());

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "Hello completeExceptionally");
        completableFuture1.completeExceptionally(new RuntimeException("Calculation failed!"));
//        completableFuture1.get();
        // A CompletableFuture<String> is created but not yet completed.
        // Instead of providing a normal result (.complete("Success")), we use .completeExceptionally(new RuntimeException("Calculation failed!")) to signal an error.
        // When .get() is called, it doesn't return a value but throws an ExecutionException because the future was completed exceptionally.

        // Async method
        CompletableFuture<String> completableFuture2
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture2
                .thenApplyAsync(s -> s + " World Async");

        System.out.println(future.get());

//        Java 9 introduced new instance methods that improve flexibility and ease of use when working with asynchronous computing:
//
//        Executor defaultExecutor()
//        CompletableFuture<U> newIncompleteFuture()
//        CompletableFuture<T> copy()
//        CompletionStage<T> minimalCompletionStage()
//        CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor)
//        CompletableFuture<T> completeAsync(Supplier<? extends T> supplier)
//        CompletableFuture<T> orTimeout(long timeout, TimeUnit unit)
//        CompletableFuture<T> completeOnTimeout(T value, long timeout, TimeUnit unit)

//        Java 9 enhancements also added support for creating and managing instances of  CompletableFuture with static utility methods:
//
//        Executor delayedExecutor(long delay, TimeUnit unit, Executor executor)
//        Executor delayedExecutor(long delay, TimeUnit unit)
//        <U> CompletionStage<U> completedStage(U value)
//        <U> CompletionStage<U> failedStage(Throwable ex)
//        <U> CompletableFuture<U> failedFuture(Throwable ex)

        Executor executor = new CompletableFuture().defaultExecutor();

        // newIncompleteFuture() creates a new CompletableFuture that is not yet completed.
        class CustomCompletableFuture<T> extends CompletableFuture<T> {
            @Override
            public <U> CompletableFuture<U> newIncompleteFuture() {
                return new CustomCompletableFuture<>();
            }
        }

        CompletableFuture<String> newIncompleteFuture = new CustomCompletableFuture<String>().newIncompleteFuture();
        System.out.println("Instance Type: " + newIncompleteFuture.getClass().getName());


        // copy()
        CompletableFuture<String> copiedCompletableFuture = CompletableFuture.supplyAsync(() -> "Hello copy").copy();
        System.out.println(copiedCompletableFuture.get());

        // minimalCompletionStage() --> Returns a CompletionStage that is equivalent to this CompletableFuture(with result and error also). use toCompletableFuture() to convert it to CompletableFuture
        CompletableFuture<String> minimalCompletionStage = CompletableFuture.supplyAsync(() -> "Hello minimalCompletionStage").minimalCompletionStage().toCompletableFuture();
        System.out.println(minimalCompletionStage.get());

        // completeAsync() --> Completes this CompletableFuture with the result of the given Supplier function asynchronously using the given Executor.
        CompletableFuture<String> completableFuture3 = new CompletableFuture<>();
        CompletableFuture<String> completableFuture4 = completableFuture3.completeAsync(() -> "Hello completeAsync");
        System.out.println(completableFuture4.get());

        // orTimeout()
        var timeCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);   // if greater than 4 seconds, then it will throw TimeoutException
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello orTimeout";
        }).orTimeout(4, TimeUnit.SECONDS);
        System.out.println(timeCompletableFuture.get());

        var completeOnTimeoutFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);   // if greater than 4 seconds, then it will throw TimeoutException
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello completeOnTimeout";
        }).completeOnTimeout(" Did not completed On Time", 3, TimeUnit.SECONDS);
        System.out.println(completeOnTimeoutFuture.get());

        // The delayedExecutor() method creates an Executor that delays execution by a specified time.
        CompletableFuture<String> delayedExecutorFuture = CompletableFuture.supplyAsync(
                () -> "delayedExecutorFuture Task Completed!",
                CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS)
        );

        // Get result (blocks until completed)
        System.out.println(delayedExecutorFuture.get());

        // completedStage() --> Returns a new CompletionStage that is already completed with the given value.
        CompletionStage<String> completedStage = CompletableFuture.completedStage("Hello, CompletedStage!");
        System.out.println(completedStage.toCompletableFuture().get());

        // failedStage() --> Returns a new CompletionStage that is already completed with the given exception.
        CompletionStage<String> failedStage = CompletableFuture.failedStage(new RuntimeException("Something went wrong!"));
        // handle exception
        failedStage.exceptionally(throwable -> {
            System.out.println("Exception: " + throwable.getMessage());
            return null;
        });
//        System.out.println(failedStage.toCompletableFuture().get());  // when get() is called, it will throw exception

        // failedFuture() --> Returns a new CompletableFuture that is already completed with the given exception.
        CompletableFuture<String> failedFuture = CompletableFuture.failedFuture(new RuntimeException("Something went wrong!"));
        failedFuture.exceptionally(ex -> {
            System.out.println("Handled Exception: " + ex.getMessage());
            return "Fallback Value"; // Returning a default value
        }).thenAccept(System.out::println);


    }
}
