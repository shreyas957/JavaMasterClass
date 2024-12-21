package com.shreyas.optional;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String name = "Shreyas";
        String name1 = null;
        Optional<String> nameOptional = Optional.of(name);
        Optional<String> nameOptional1 = Optional.ofNullable(name1);
        System.out.println(nameOptional.isEmpty());
        System.out.println(nameOptional1.isEmpty());

        // What is the use of optional?
        // Optional is a container object used to contain not-null objects.
        // Optional object is used to represent null with absent value.
        // This class has various utility methods to facilitate code to
        // handle values as ‘available’ or ‘not available’ instead of checking null values.
    }
}
