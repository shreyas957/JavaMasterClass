package com.shreyas.oop.abstraction;

public abstract class Animal {
    protected String type;
    private String size;
    private double weight;

    public Animal(String type, String size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public abstract void move(String speed);  //If I insert curly braces to the abstract method it will show an error.
    public abstract void makeNoise();
    public String explicitClassName(){
        return getClass().getSimpleName() + "(( " + type + " ))";
    }


}
