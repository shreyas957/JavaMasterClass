package com.shreyas.collectionsInJava.concurrentcollections;

import com.shreyas.oop.interfaces.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Stream;


/**
 * {@code Synchronized Collections} are implemented using locks which protects the collection from concurrent access.<br>
 * This means a single lock is used to synchronize the entire map<br>
 * example<br>
 * {@code Collections.synchronizedXXX()}
 * <br>
 * <br>
 * {@code Concurrent Collections} are more efficient than synchronized collections because they use fine-grained locking mechanism.<br>
 * or non-blocking algorithms to provide thread safety.<br>
 * example<br>
 * collections from {@code java.util.concurrent}
 */
public class Examples {
    public static void main(String[] args) {
        // Why hashMap is not thread safe
        // 1. lack of synchronization
        // 2. multiple threads can access the same bucket
        // 3. No guarantee of memory consistency
        // 4. ConcurrentModificationException can be thrown if the map is modified while iterating over it

        // ConcurrentHashMap is thread safe
        Map<Integer, Integer> map1 = new ConcurrentHashMap<>();  // concurrent hashMap
        Map<Integer, Integer> map2 = Collections.synchronizedMap(new HashMap<>());  // synchronized hashMap
        ConcurrentSkipListMap<Integer, Integer> map3 = new ConcurrentSkipListMap<>();  // concurrent skip list map
        // Difference between ConcurrentHashMap and ConcurrentSkipListMap is that
        // ConcurrentHashMap is a hash table whereas ConcurrentSkipListMap is a skip list
        // skip-list is data structure that allows fast search within an ordered sequence of elements


        // We can use terminal operations toArray() or toList() with parallel streams.
        var threadMap = new ConcurrentSkipListMap<String, Long>();

        var persons = Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .peek(p -> {
                    var threadName = Thread.currentThread().getName();
                    threadMap.merge(threadName, 1L, Long::sum);
                })
                .toArray(Person[]::new);
        System.out.println(persons.length);
        System.out.println(threadMap);
        long total = threadMap.values().stream().mapToLong(Long::longValue).sum();
        System.out.println("Total: " + total);

        System.out.println("*".repeat(30));

        // Concurrent Collections for arrays and list
        var list = Collections.synchronizedList(new ArrayList<Integer>());

        // Use ConcurrentLinkedQueue for frequent insertions and removals, e.g. task scheduling
        // CopyOnWriteArrayList for frequent reads and occasional writes.
        // ArrayBlockingQueue for bounded queues(fixed size), that blocks under two circumstances,
        // 1. When we try to poll/remove element from empty queue
        // 2. When we try to add element to a full queue




    }
}
