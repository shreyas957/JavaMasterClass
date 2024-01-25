package com.shreyas.functionalProgramming;

import java.util.function.Consumer;

public class LambdaExpressions {
    public static void main(String[] args) {
        Consumer<String> consume = name -> System.out.println(name);
        consume.accept("Shreyas");
        // many more
        
    }
}
