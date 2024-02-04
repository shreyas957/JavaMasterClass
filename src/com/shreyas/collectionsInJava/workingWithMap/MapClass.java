package com.shreyas.collectionsInJava.workingWithMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class Diamond {
    String place;

    public Diamond(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Diamond{" +
                "place='" + place + '\'' +
                '}';
    }
}

public class MapClass {
    public static void main(String[] args) {
        Map<Person, Diamond> personDiamondMap = new HashMap<>();
        personDiamondMap.put(new Person("Shreyas"), new Diamond("Indian"));
        personDiamondMap.put(new Person("Shreeya"), new Diamond("Brazil"));

        System.out.println(new Person("Sam").hashCode());
        System.out.println(new Person("Sam").hashCode());
        System.out.println(personDiamondMap.get(new Person("Shreyas")));

        System.out.println();

        for (Map.Entry<Person, Diamond> entry : personDiamondMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        for (Person p : personDiamondMap.keySet()) {
            System.out.println(p.name);
        }

        for (Diamond d : personDiamondMap.values()) {
            System.out.println(d.place);
        }

    }

    private static void hashMapMethod() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Shreyas");
        map.put(2, "Shreeya");

        System.out.println(map);
        System.out.println(map.get(1));
        System.out.println(map.size());
        System.out.println(map.containsKey(3));
    }
}
