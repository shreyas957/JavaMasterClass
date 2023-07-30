package com.shreyas.oop.interfaces;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        Bicycle bicycle = new Bicycle();

        Vehicle[] vehicles = {
                car,
                bicycle
        };

        Person person = new Person("John", vehicles);

        for (var vehicle : person.getVehicles()) {
            vehicle.move(10);
            System.out.println(vehicle.getCurrentSpeed());
        }

        System.out.println(person.toString());
        System.out.println("Purchase rate: " + Vehicle.PURCHASE_RATE);
        System.out.println("Miles to Km: " + car.milesToKm());
    }
}
