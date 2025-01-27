package com.shreyas.concurrency.synchronization.locks;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageRepository {
    private String message;
    private boolean hasMessage = false;
    private final Lock lock = new ReentrantLock(); // It has same behavior as intrinsic lock and it is mutually exclusive.

    public String read() {
        // Below code is nothing bus just using synchronized keyword --> deadlock can occur
//        lock.lock();
//        try {
//            while (!hasMessage) {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        hasMessage = false;
//        } finally {
//            lock.unlock();
//        }

        if (lock.tryLock()) {
            try {
                while (!hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = false;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("read blocked" + lock);
            hasMessage = false;
        }

        return message;
    }

    public synchronized void write(String message) {

        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    while (hasMessage) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    hasMessage = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("write blocked" + lock);
                hasMessage = true;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.message = message;
    }
}

class MessageWriter implements Runnable {
    private MessageRepository outgoingMessage;
    private final String text = """
            Hi, I am Shreyas.
            I am a software engineer.
            I am learning Java Concurrency.""";

    public MessageWriter(MessageRepository outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            outgoingMessage.write(lines[i]);
            try {
                Thread.sleep(random.nextInt(200, 500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        outgoingMessage.write("finish");
    }
}

class MessageReader implements Runnable {
    private MessageRepository incomingMessage;

    public MessageReader(MessageRepository incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String lastMessage = "";
        do {
            try {
                Thread.sleep(random.nextInt(200, 500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lastMessage = incomingMessage.read();
            System.out.println(lastMessage);
        } while (!lastMessage.equals("finish"));
    }
}

public class LocksExample {
    public static void main(String[] args) {
        // shared object
        MessageRepository messageRepository = new MessageRepository();

        Thread reader = new Thread(new MessageReader(messageRepository), "Reader");
        Thread writer = new Thread(new MessageWriter(messageRepository), "Writer");

        writer.setUncaughtExceptionHandler((thread, exc) -> {
            System.out.println("Exception occurred in writer thread : " + exc.getMessage());
            if (reader.isAlive()) {
                System.out.println("Going to interrupt reader thread");
                reader.interrupt();
            }
        });

        reader.setUncaughtExceptionHandler((thread, exc) -> {
            System.out.println("Exception occurred in reader thread : " + exc.getMessage());
            if (writer.isAlive()) {
                System.out.println("Going to interrupt writer thread");
                writer.interrupt();
            }
        });
        reader.start();
        writer.start();
    }
}
