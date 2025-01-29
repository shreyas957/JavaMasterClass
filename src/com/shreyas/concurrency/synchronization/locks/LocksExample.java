package com.shreyas.concurrency.synchronization.locks;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A repository for storing and retrieving messages with thread-safe operations.
 */
class MessageRepository {
    private String message;
    private boolean hasMessage = false;
    private final Lock lock = new ReentrantLock(); // It has the same behavior as an intrinsic lock and it is mutually exclusive.

    /**
     * Reads the message from the repository.
     *
     * @return the message if available, otherwise blocks until a message is available.
     */
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

    /**
     * Writes a message to the repository.
     *
     * @param message the message to write.
     */
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

/**
 * A runnable that writes messages to a MessageRepository.
 */
class MessageWriter implements Runnable {
    private MessageRepository outgoingMessage;
    private final String text = """
            Hi, I am Shreyas.
            I am a software engineer.
            I am learning Java Concurrency.""";

    /**
     * Constructs a MessageWriter with the specified MessageRepository.
     *
     * @param outgoingMessage the MessageRepository to write messages to.
     */
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

/**
 * A runnable that reads messages from a MessageRepository.
 */
class MessageReader implements Runnable {
    private MessageRepository incomingMessage;

    /**
     * Constructs a MessageReader with the specified MessageRepository.
     *
     * @param incomingMessage the MessageRepository to read messages from.
     */
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

/**
 * Demonstrates the use of locks for thread-safe message reading and writing.
 */
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