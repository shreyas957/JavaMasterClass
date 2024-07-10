package com.shreyas.oop.interfaces;

public interface Vehicle {
    // Constants : (public static and final --> fields)
    // abstract methods : automatically defined as public abstract.
    // default methods
    // static methods

    double PURCHASE_RATE = 100.0;

    void move(int amount);
    void applyBreaks(int amount);
    int getCurrentSpeed();

    default double milesToKm() {
        return getCurrentSpeed() * 1.609;
    }
}
