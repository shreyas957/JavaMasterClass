package com.shreyas.concurrency.synchronization.waitandnotify;

import java.util.Random;

/**
 * The recommended approach to waiting is to check the condition being awaited in a while loop around the call to wait,
 * as shown in the example below. Among other things, this approach avoids problems that can be caused by spurious wakeups.
 * <p>
 * {@code
 * synchronized (obj) {
 * while (<condition does not hold> and <timeout not exceeded>) {
 * long timeoutMillis = ... ; // recompute timeout values
 * int nanos = ... ;
 * obj.wait(timeoutMillis, nanos);
 * }
 * ... // Perform action appropriate to condition or timeout
 * }
 * }
 */

class MessageRepository {
    private String message;
    private boolean hasMessage = false;

    public synchronized String read() {
        while (!hasMessage) {
            // The recommended approach to waiting is to check the condition being awaited in a while loop around the call to wait
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hasMessage = false;
        notifyAll();
        return message;
    }

    public synchronized void write(String message) {
        while (hasMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hasMessage = true;
        notifyAll();
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

public class WaitAndNotify {
    public static void main(String[] args) {
        // shared object
        MessageRepository messageRepository = new MessageRepository();

        Thread reader = new Thread(new MessageReader(messageRepository));
        Thread writer = new Thread(new MessageWriter(messageRepository));
        reader.start();
        writer.start();

        // Without wait() and notify() In this above condition, we are facing deadlock situation.
        // The reader is waiting for the message to be written, but the writer is waiting for the reader to read the message.
        // If one thread acquires the lock, another thread must wait for the lock to be released. so tht it can change the condition.
        // thus deadlock situation occurs.

        // with wait() and notify() we can avoid deadlock situation.
        // wait() method causes the current thread to wait until another thread invokes the notify() method or the notifyAll() method for this object.
    }
}
