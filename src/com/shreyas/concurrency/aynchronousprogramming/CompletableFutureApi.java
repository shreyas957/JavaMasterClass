package com.shreyas.concurrency.aynchronousprogramming;

import java.util.concurrent.*;
import java.util.function.Supplier;

class CompletableFutureApiExample {
    private final ExecutorService executorService;

    CompletableFutureApiExample(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            completableFuture.complete("Hello");  // set the value returned by Future.get()
        });
        return completableFuture;
    }

    public CompletableFuture<String> completeFutureMethod() {
        return CompletableFuture.completedFuture("I know this result");
    }

    // Running with encapsulated computational logic, we can also pass ExecutorService to runAsync
    public void runAsyncExample(Runnable runnable) {
        CompletableFuture.runAsync(runnable);
    }

    // Running encapsulated computational logic with Suppler, we can also pass ExecutorService to supplyAsync
    public <T> CompletableFuture<T> supplyAsyncMethod(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier);
    }

}

public class CompletableFutureApi {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var executorService = Executors.newCachedThreadPool();
        CompletableFutureApiExample completableFutureApiExample = new CompletableFutureApiExample(executorService);

        Future<String> completableFuture = completableFutureApiExample.calculateAsync();
        System.out.println(completableFuture.get());    // this will block until we get result future.
        executorService.shutdown();

        System.out.println("*".repeat(20));

        System.out.println(completableFutureApiExample.completeFutureMethod().get());

        System.out.println("*".repeat(20));

        completableFutureApiExample.runAsyncExample(() -> {
            System.out.println(Thread.currentThread().getName() + " is running with runAsync method");
        });

        System.out.println("*".repeat(20));

        CompletableFuture<String> completableFuture1 = completableFutureApiExample.supplyAsyncMethod(() -> {
            return "I am running with supplyAsync method";
        });
        System.out.println(completableFuture1.get());   // get blocks till result arrives
        System.out.println(completableFuture1.join());  // join returns value when it is ready

        // Processing the result of CompletableFuture
        CompletableFuture<String> applyThenFuture = completableFuture1.thenApply(
                result -> result + " .....processed");
        System.out.println(applyThenFuture.get());

        CompletableFuture<Void> thenAcceptFuture = completableFuture1.thenAccept(
                result -> System.out.println("Consuming result: " + result));
        thenAcceptFuture.get();

        CompletableFuture<Void> thenRunFuture = completableFuture1.thenRun(
                () -> System.out.println(" thenRun : I am running after supplyAsync"));
        thenRunFuture.get();

        System.out.println("*".repeat(20));

        // Combining two CompletableFutures
        CompletableFuture<String> thenComposeFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        System.out.println(thenComposeFuture.get());

        CompletableFuture<String> thenCombineFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(
                                () -> " World with thenCombine"),
                        (s1, s2) -> s1 + s2);
        System.out.println(thenCombineFuture.get());

        CompletableFuture<Void> thenAcceptBothFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World with thenAcceptBoth"),
                        (s1, s2) -> System.out.println(s1 + s2));

    }
}
