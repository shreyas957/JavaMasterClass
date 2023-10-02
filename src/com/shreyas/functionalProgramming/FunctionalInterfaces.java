package com.shreyas.functionalProgramming;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        Function<Integer, Integer> incrementByOne = number -> number+1;

        Function<Integer, Integer> multiplyBy10 = number -> number*10;

        Integer increment = incrementByOne.apply(1);
        System.out.println(increment);
        Function<Integer, Integer> result = incrementByOne.andThen(multiplyBy10);
        System.out.println(result.apply(1));

        BiFunction<Integer, Integer, Integer> incrementAndMultiply =
                (number1, number2) -> (number1+1)*number2;

        System.out.println(incrementAndMultiply.apply(4, 10));


        String name = "Shreyas";
        Consumer<String>greet = temp -> System.out.println("Hello " + temp);
        greet.accept(name);
        // BiConsumers are the also same.


        // Predicate --> takes one type and return boolean
        Predicate<String>isLengthGreaterThan5 = len -> len.length() > 5;
        Predicate<String>isContainsS = temp -> temp.contains("s");
        System.out.println(isLengthGreaterThan5.test(name));
        System.out.println(isLengthGreaterThan5.test("Hi"));
        System.out.println(isLengthGreaterThan5.and(isContainsS).test(name));

        // Supplier
        Supplier<String>getJdbcURL = () -> "jdbc://localhost:5432/users";
        System.out.println(getJdbcURL.get());


        // AND MANY MORE
    }
}
