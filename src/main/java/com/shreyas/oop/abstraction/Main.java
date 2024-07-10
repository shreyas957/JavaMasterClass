package com.shreyas.oop.abstraction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("WoLf", "Big", 100);
        dog.makeNoise();
        System.out.println("-".repeat(10));
        Animal cat = new Dog("Cat", "Small", 10);
        doAnimalStuff(cat);

        List<Animal> animals = new ArrayList<>();
        animals.add(dog);
        animals.add(new Dog("German Shepard", "Big", 100));
        animals.add(new Fish("Goldfish", "small", 10));
        animals.add(cat);

        for(var it : animals){
          doAnimalStuff(it);
        }
    }
    private static void doAnimalStuff(Animal animal){
        animal.makeNoise();
        animal.move("slow");
        System.out.println("-".repeat(20));
    }

}


