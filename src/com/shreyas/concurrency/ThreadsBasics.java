package com.shreyas.concurrency;

public class ThreadsBasics {
    public static void main(String[] args) {
        var currentThread = Thread.currentThread();
        System.out.println("Current Thread: " + currentThread.getClass().getName());
        System.out.println(currentThread);
        printThreadState(currentThread);

        currentThread.setName("MainGuy");
        currentThread.setPriority(Thread.MAX_PRIORITY);
        printThreadState(currentThread);
    }
    public static void printThreadState(Thread thread) {
        System.out.println("---------------------------------");
        System.out.println("Thread Name: " + thread.getName());
        System.out.println("Thread ID: " + thread.getId());
        System.out.println("Thread Priority: " + thread.getPriority());
        System.out.println("Thread State: " + thread.getState());
        System.out.println("Thread Group: " + thread.getThreadGroup());
        System.out.println("Thread is Alive: " + thread.isAlive());
    }
}
