package com.shreyas.functionalProgramming;

import java.io.PrintStream;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class PlainOld {
    private static int last_id = 1;
    private int id;

    public PlainOld() {
        this.id = PlainOld.last_id++;
        System.out.println("Creating new object with id: " + id);
    }
}

public class MethodReferences {
    public static void main(String[] args) {

        // In below example of method reference we have invoked static method using Type reference
        calculator(10, 5, Integer::sum);    // (a, b) -> a+b
        BinaryOperator<Double> doubleSum = Double::sum;
        calculator(10.3, 12.4, doubleSum);
        System.out.println("-".repeat(20));

        // The give method reference is constructor method reference
        Supplier<PlainOld> supplier = PlainOld::new;    // lambda : () -> new PlainOld()
        PlainOld[] newPojoArray = seedArray(supplier, 5);
        System.out.println("-".repeat(20));

        // 3rd type : Instance method invocation  (2 Types)
        List<String> names = new ArrayList<>(List.of("Tom", "David", "Jerry"));
        // Here System.out gives instance of PrintStream on which println (instance method) is invoked. (external instance method reference)
        PrintStream out = System.out;
        names.forEach(out::println);    //  (s) -> System.out.println(s)
        // (s1, s2) -> s1.concat(s2)  ... s1 is instance on which concat is invoked  this is instance passed to method argument
        calculator("Hello ", "World", String::concat);
        System.out.println("-".repeat(20));


        // Convenience methods on lambda expression (chaining)
        Function<String, String> uCase = String::toUpperCase;
        Function<String, String> concatLastName = s -> s.concat("Shevale ");
        Function<String, String> uCaseLastName = uCase.andThen(concatLastName);     // run uCase and then concatLastName
        System.out.println(uCaseLastName.apply("Shreyas "));

        uCaseLastName = uCase.compose(concatLastName);      // 1st concat and then uppercase
        System.out.println(uCaseLastName.apply("Shreyas "));

        Function<String, String[]> f1 = uCase.andThen(s -> s.concat("Shevale"))
                .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(f1.apply("Shreyas ")));

        Function<String, String[]> f2 = uCase.compose(concatLastName)
                .andThen(s -> s.split(" "));
        System.out.println(Arrays.toString(f2.apply("Shreyas ")));
        System.out.println("-".repeat(20));


        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> nonEmpty = String::isEmpty;
        Predicate<String> shorterThan5 = s -> s.length() < 5;
        Predicate<String> p = nonNull.and(nonEmpty).and(shorterThan5);

        record Person(String firstName, String lastName) {
        }
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("Shreyas", "Shevale"),
                new Person("Vikas", "Gouda"),
                new Person("Sanket", "Jagtap"))
        );

//        people.sort((o1, o2) -> o1.firstName.compareTo(o2.firstName));
//        people.sort(Comparator.comparing(o -> o.firstName));

//        The Comparator.comparing() method is used to create a comparator that compares objects
//        based on a given key extractor function (Person::firstName in this case).
//        This will sort the list in ascending order based on the first names.
        Function<Person, String> firstName = Person::firstName;
        people.sort(Comparator.comparing(firstName));
        System.out.println(people);

        people.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName).reversed());
        System.out.println(people);

    }

    private static <T> void calculator(T t1, T t2, BinaryOperator<T> function) {
        T result = function.apply(t1, t2);
        System.out.println("Result : " + result);
    }

    private static PlainOld[] seedArray(Supplier<PlainOld> reference, int count) {
        PlainOld[] array = new PlainOld[count];
        Arrays.setAll(array, i -> reference.get());
        return array;
    }
}
