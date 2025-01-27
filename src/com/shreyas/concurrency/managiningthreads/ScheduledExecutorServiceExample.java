package com.shreyas.concurrency.managiningthreads;

import com.shreyas.concurrency.ThreadColour;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) {
        var dtf = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM,
                FormatStyle.LONG
        );

        Callable<ZonedDateTime> waitThenDoIt = () -> {
            ZonedDateTime zonedDateTime = null;
            try {
                TimeUnit.SECONDS.sleep(2);
                zonedDateTime = ZonedDateTime.now();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return zonedDateTime;
        };

        var threadPool = Executors.newFixedThreadPool(2);
        List<Callable<ZonedDateTime>> list = Collections.nCopies(4, waitThenDoIt);
        try {
            System.out.println("---> " + ZonedDateTime.now().format(dtf));
            var futureList = threadPool.invokeAll(list);
            for (var future : futureList) {
                System.out.println(future.get(1, TimeUnit.SECONDS).format(dtf));
            }
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }

        // When threadPool Size is 4
//        ---> Jan 21, 2025, 3:49:47 PM IST
//        Jan 21, 2025, 3:49:49 PM IST
//        Jan 21, 2025, 3:49:49 PM IST
//        Jan 21, 2025, 3:49:49 PM IST
//        Jan 21, 2025, 3:49:49 PM IST

        // when threadPool size id 2
//        ---> Jan 21, 2025, 3:50:41 PM IST
//        Jan 21, 2025, 3:50:43 PM IST
//        Jan 21, 2025, 3:50:43 PM IST
//        Jan 21, 2025, 3:50:45 PM IST
//        Jan 21, 2025, 3:50:45 PM IST


//        exampleOne(dtf);
//        exampleTwo(dtf);
        exampleThree(dtf);
    }

    private static void exampleOne(DateTimeFormatter dtf) {
        System.out.println("---> " + ZonedDateTime.now().format(dtf));
        var scheduledThreadPool = Executors.newScheduledThreadPool(4);
        for (int i = 0; i < 3; i++) {
            scheduledThreadPool.schedule(() -> {
                System.out.println(ZonedDateTime.now().format(dtf));
            }, 2 * (i + 1), TimeUnit.SECONDS);
        }
//        ---> Jan 21, 2025, 4:00:14 PM IST
//        Jan 21, 2025, 4:00:16 PM IST  // 2 sec delay
//        Jan 21, 2025, 4:00:18 PM IST // 4 sec delay
//        Jan 21, 2025, 4:00:20 PM IST // 6 sec delay
        scheduledThreadPool.shutdown();
    }

    private static void exampleTwo(DateTimeFormatter dtf) {
        System.out.println("---> " + ZonedDateTime.now().format(dtf));
        var scheduledThreadPool = Executors.newScheduledThreadPool(4);

        // This will execute with initial delay of 2 sec and then every 2 sec
//        scheduledThreadPool.scheduleWithFixedDelay(() -> {
//            System.out.println(ZonedDateTime.now().format(dtf));
//        }, 2, 2, TimeUnit.SECONDS);

        // Above code will not print anything because the shutdown method shutdowns
        // the scheduledThreadPool, before Future ones are started
        // The issue lies in the fact that shutdown() is called immediately after
        // the scheduling statement. When shutdown() is invoked,
        // the ScheduledThreadPoolExecutor is stopped before the initial delay completes,
        // so no tasks are executed.


        // In below Implementation, a task will be executed with initial delay of 2 sec
        // And then with delay of 2 sec after execution of previous task.
        // So if my task takes 3 sec to run and then 2 sec delay between them then it will be total of 5 sec.
        ScheduledFuture<?> scheduledFuture = scheduledThreadPool.scheduleWithFixedDelay(() -> {
            System.out.println(ZonedDateTime.now().format(dtf));
        }, 2, 2, TimeUnit.SECONDS);

        long time = System.currentTimeMillis();

        while (!scheduledFuture.isDone()) {
            try {
                TimeUnit.SECONDS.sleep(2);
                if ((System.currentTimeMillis() - time) > 10_000) {
                    scheduledFuture.cancel(true);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        scheduledThreadPool.shutdown();
    }

    private static void exampleThree(DateTimeFormatter dtf) {
        Runnable dateTask = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(ThreadColour.ANSI_YELLOW.color() + " A : " + ZonedDateTime.now().format(dtf));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        System.out.println("---> " + ZonedDateTime.now().format(dtf));
        var scheduledThreadPool = Executors.newScheduledThreadPool(4);

        // With fixed rate method, next task that gets scheduled may be scheduled before the previous task completes, and they'll queue up.
        // with delayed rate method the next task will be scheduled after the previous task completes.

        // A task execute at every 3 sec, because scheduled task manager is scheduling task
        // at every 2 sec, but the task exceeds that time thus new task will be scheduled immediately,
        // and delay period will be ignored.
        ScheduledFuture<?> scheduledFuture = scheduledThreadPool.scheduleAtFixedRate(
                dateTask, 2, 2, TimeUnit.SECONDS);

        // B task executed at every 2 sec. because it's task quickly in the interval of 2 sec.
        ScheduledFuture<?> scheduledFuture2 = scheduledThreadPool.scheduleAtFixedRate(
                () -> System.out.println(ThreadColour.ANSI_RED.color() + " B : " + ZonedDateTime.now().format(dtf)),
                2, 2, TimeUnit.SECONDS);

        long time = System.currentTimeMillis();

        while (!scheduledFuture.isDone()) {
            try {
                TimeUnit.SECONDS.sleep(2);
                if ((System.currentTimeMillis() - time) > 10_000) {
                    scheduledFuture.cancel(true);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        scheduledThreadPool.shutdown();
    }
}
