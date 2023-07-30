package com.shreyas.dataStructures.workingWithQueue;

import java.util.LinkedList;
import java.util.Queue;

record Person(String name, int age) {

}

public class queue {
    public static void main(String[] args) {
        Queue<Person> market = new LinkedList<>();
        market.add(new Person("Alex", 21));
        market.add(new Person("Sam", 22));
        market.offer(new Person("Nelson", 30));

        System.out.println(market.peek());
        System.out.println(market.size());
        System.out.println(market.poll()); //Remove
        System.out.println(market.size());
    }
}
