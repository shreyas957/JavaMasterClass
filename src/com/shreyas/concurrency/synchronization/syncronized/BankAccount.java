package com.shreyas.concurrency.synchronization.syncronized;

/**
 * This class will be accessed by multiple threads.
 */
public class BankAccount {
    private double balance;
    private String name;
    private final Object lockName = new Object();
    private final Object lockBalance = new Object();

    public BankAccount(String name, double balance) {
        this.balance = balance;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        synchronized (this.lockName) {
            this.name = name;
            System.out.println("updated Name :" + name);
        }
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void depositFunds(double funds) {
        try {
            System.out.println("Taking a nap for 7 seconds");
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // By not using synchronized at method, rather using synchronized block
        // I do not have to wait for the entire method to complete. thus, reducing the waiting time.
        synchronized (this.lockBalance) {  // passing object instance to synchronize block
            double originalBalance = balance;
            this.balance += funds;
            System.out.println("OriginalBalance : " + originalBalance + " New Balance : " + balance);
            addPromoDollars(funds);
        }
    }

    private void addPromoDollars(double funds) {
        if (funds >= 5000) {
            synchronized (lockBalance) {
                System.out.println("Adding promo dollars");
                balance += 25;
            }
        }
    }

    public synchronized void withdrawFunds(double funds) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        double originalBalance = balance;
        if (originalBalance < funds) {
            System.out.println("Insufficient funds : " + originalBalance);
            return;
        }
        this.balance -= funds;
        System.out.println("OriginalBalance : " + originalBalance + " New Balance : " + balance);
    }


}
