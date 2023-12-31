package com.shreyas.oop.composition;

public class Product {
    private String model;
    private String manufacturer;

    public Product(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }
}

class Monitor extends Product{
    private int size;
    private String resolution;

    public Monitor(String model, String manufacturer) {
        super(model, manufacturer);
    }
    public Monitor(String model, String manufacturer, int size, String resolution) {
        super(model, manufacturer);
        this.size = size;
        this.resolution = resolution;
    }

    public void drawPixelAt(int x, int y, String color){
        System.out.println("Drawing pixel at " + x + " and " + y + " of color " + color);
    }
}

class MotherBoard extends Product{
    private int ramSlot;
    private int cardSlot;
    private String bios;

    public MotherBoard(String model, String manufacturer) {
        super(model, manufacturer);
    }
    public MotherBoard(String model, String manufacturer, int ramSlot, int cardSlot, String bios) {
        super(model, manufacturer);
        this.ramSlot = ramSlot;
        this.cardSlot = cardSlot;
        this.bios = bios;
    }

    public void loadProgram(String programName){
        System.out.println(programName = " is loading....");
    }
}

class ComputerCase extends Product{
    private String powerSupply;

    public ComputerCase(String model, String manufacturer) {
        super(model, manufacturer);
    }
    public ComputerCase(String model, String manufacturer, String powerSupply) {
        super(model, manufacturer);
        this.powerSupply = powerSupply;
    }
    public void pressPowerButton(){
        System.out.println("Power button pressed!!");
    }
}