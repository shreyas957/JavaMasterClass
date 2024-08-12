package com.shreyas.oop.abstraction;

public class Dog extends Animal{

    public Dog(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        if(speed.equalsIgnoreCase("Slow")){
            System.out.println(explicitClassName() + " moving slowly");
        }else{
            System.out.println(explicitClassName() + " moving fast");
        }
    }

    @Override
    public void makeNoise() {
        if(type.equalsIgnoreCase("Wolf")){
            System.out.println("Howling!!..");
        }else if(type.equalsIgnoreCase("Cat")){
            System.out.println("Meow!!....");
        }else{
            System.out.println("Woof!!..");
        }
    }
}
