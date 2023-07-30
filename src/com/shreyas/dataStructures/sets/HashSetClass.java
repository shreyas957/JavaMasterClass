package com.shreyas.dataStructures.sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetClass {
    public static void main(String[] args) {
        Set<Integer> num = new HashSet<>();
        num.add(1);
        num.add(3);
        num.add(2);

        Iterator<Integer> iterator = num.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
