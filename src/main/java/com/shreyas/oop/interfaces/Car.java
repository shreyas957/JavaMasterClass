package com.shreyas.oop.interfaces;

public class Car implements Vehicle {
    private int currentSpeed;

    public Car(){

    }
    public Car(int currentSpeed){
        this.currentSpeed = currentSpeed;
    }

    @Override
    public void move(int amount) {
        System.out.println("Starting engin");
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
        return "Car{" +
                "currentSpeed=" + currentSpeed +
                '}';
    }
}
