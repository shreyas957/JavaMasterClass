package com.shreyas.oop.abstraction;

public class Fish extends Animal{
    public Fish(String type, String size, double weight) {
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
        if(type.equalsIgnoreCase("Goldfish")){
            System.out.println("Swing!!..");
        }else if(type.equalsIgnoreCase("Piranha")){
            System.out.println("Shushhh!!....");
        }else{
            System.out.println("splash!!..");
        }
    }
}
