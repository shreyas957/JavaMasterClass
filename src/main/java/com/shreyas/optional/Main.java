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


    }
}
