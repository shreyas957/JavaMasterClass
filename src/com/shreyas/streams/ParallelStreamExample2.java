package com.shreyas.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Person(String firstName, String lastName, int age) {
    private final static String[] firsts = {"John", "Jane", "Jack", "Jill", "Joe"};
    private final static String[] lasts = {"Doe", "Smith", "Johnson", "Brown", "Taylor"};

    private final static Random random = new Random();

    public Person() {
        this(firsts[random.nextInt(firsts.length)],
                lasts[random.nextInt(lasts.length)],
                random.nextInt(100));
    }

    @Override
    public String toString() {
        return "%s %s, (%d)".formatted(lastName, firstName, age);
    }
}

public class ParallelStreamExample2 {
    public static void main(String[] args) {
        var people = Stream.generate(Person::new)
                .limit(10)
                .sorted(Comparator.comparing(Person::lastName))
                .toArray();

        for (var person : people) {
            System.out.println(person);
        }

        System.out.println("*".repeat(30));

        Arrays.stream(people)
                .limit(10)
                .parallel()
                .forEachOrdered(System.out::println);
        // Diff in forEach() and forEachOrdered() is that forEachOrdered() will maintain the order of the stream as per the source stream.
        // forEach() will not maintain the order of the stream as per the source stream.

        Stream.generate(Person::new)
                .limit(10)
                .parallel()
                .sorted(Comparator.comparing(Person::lastName))    // Redundant 'sorted()' call: subsequent 'forEach()' operation doesn't depend on the sort order for parallel streams
                .forEach(System.out::println);


        System.out.println("*".repeat(30));

        int sum = IntStream.range(1, 101)
                .parallel()
                .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);

        System.out.println("*".repeat(30));

        String sentence = """
                Hi, I am Shreyas.
                I am a software engineer.
                I am learning Java 16. I am learning Java 16 streams.
                """;
        List<String> list = new Scanner(sentence).tokens().toList();
        list.forEach(System.out::println);

        var backTogether = list.stream()   // use of parallelStream will throw error because String Joiner is not threadSafe
                .reduce(
                        new StringJoiner(" "),
                        StringJoiner::add,
                        StringJoiner::merge);
        System.out.println(backTogether);

        System.out.println(list.parallelStream()
                .collect(Collectors.joining(" ")));  // This is thread safe

        System.out.println(list.parallelStream()
                .reduce("", (s1, s2) -> s1.concat(s2).concat(" ")));
        // When we create a reduction, each thread will use identity to create a new instance
        // It will then start processing some part of data using accumulator,
        // to combine its queue of data. It will then again use accumulator to join its
        // reduction to other threads results.

// Collecting
        Map<String, Long> lastNameCounts = Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .collect(Collectors.groupingBy(Person::lastName, Collectors.counting()));
        lastNameCounts.entrySet().forEach(System.out::println);
        System.out.println(lastNameCounts.getClass().getName());
        // Difference in groupingBy and groupingByConcurrent is that groupingByConcurrent will use ConcurrentHashMap
        // the advantage of using groupingByConcurrent is that it is thread safe and will not throw ConcurrentModificationException


    }
}
