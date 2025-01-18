package com.shreyas.concurrency.synchronization;

/**
 * This class will be accessed by multiple threads.
 */
public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void depositFunds(double funds) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double originalBalance =  balance;
        this.balance += funds;
        System.out.println("OriginalBalance : " + originalBalance + " New Balance : " + balance);
    }

}
