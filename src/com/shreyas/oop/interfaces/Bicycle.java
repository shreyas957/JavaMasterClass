package com.shreyas.oop.interfaces;

public class Bicycle implements Vehicle{
    private int currentSpeed;

    public Bicycle(){

    }
    public Bicycle(int currentSpeed){
        this.currentSpeed = currentSpeed;
    }

    @Override
    public void move(int amount) {
        System.out.println("Peddle");
        this.currentSpeed += amount;
    }

    @Override
    public void applyBreaks(int amount) {
        this.currentSpeed -= amount;
    }

    @Override
    public int getCurrentSpeed() {
        return currentSpeed;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "currentSpeed=" + currentSpeed +
                '}';
    }
}
