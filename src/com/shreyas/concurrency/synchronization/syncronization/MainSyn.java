package com.shreyas.concurrency.synchronization.syncronization;

public class MainSyn {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Shreyas", 10000);
        Thread t1 = new Thread(() -> {
            account.withdrawFunds(2500);
        });
        Thread t2 = new Thread(() -> {
            account.depositFunds(5000);
        });
        Thread t3 = new Thread(() -> {
            account.setName("Shreeya");
        });
        Thread t4 = new Thread(() -> {
            account.withdrawFunds(5000);
        });
        t1.start();
        t2.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Balance : " + account.getBalance());
    }
}
