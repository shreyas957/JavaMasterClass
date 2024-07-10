package com.shreyas.collectionsInJava.workingWithQueue;

import java.util.ArrayDeque;
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

        Queue<Integer> queue = new ArrayDeque<>();  // Can be used as Stack as well as queue (faster than linkedList and stack itself)
    }
}
