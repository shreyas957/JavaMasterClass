package com.shreyas.collectionsInJava.tree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>(List.of(1, 2, 3, 4));
        System.out.println(treeSet);
        System.out.println(treeSet.ceiling(0));

        record Person(String name) {
        }
        // Tree is sorted collection, so for basic data types we don't need to provide comparator,
        // But when it comes to user defined types we need to provide the comparator.
        NavigableSet<Person> set = new TreeSet<>(Comparator.comparing(Person::name));

    }
}
