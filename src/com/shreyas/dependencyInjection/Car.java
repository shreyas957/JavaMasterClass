package com.shreyas.dependencyInjection;

public class Car {
    private String brand;
    private int Reg;

    public Car(String brand, int reg) {
        this.brand = brand;
        Reg = reg;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getReg() {
        return Reg;
    }

    public void setReg(int reg) {
        Reg = reg;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", Reg=" + Reg +
                '}';
    }
}
