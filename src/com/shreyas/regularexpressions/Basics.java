package com.shreyas.regularexpressions;

import java.util.Scanner;

public class Basics {
    public static String formatted(String regx, String... args) {
        int index = 0;
//        while (regx.contains("%s")) {
//            regx = regx.replaceFirst("%s", args[index++]);
//        }
        while (regx.matches("")) {
            regx = regx.replaceFirst("%s", args[index++]);
        }
        return regx;
    }

    public static void main(String[] args) {
        String hello = "%s %s".formatted("Hello", "World");
        System.out.println(hello);
        System.out.println(Basics.formatted("%s %s Shreyas", "Hello", "World"));
        System.out.println("-".repeat(20));

        String s = "Anyone can learn abc's, 123's and any regular expression";
        System.out.println(s.replaceFirst("abc", "(-)"));   // Replace entire abc with matching abc
        System.out.println(s.replaceFirst("[abc]", "(-)")); // replace a OR b OR c
        System.out.println(s.replaceFirst("ab|bc", "(-)")); // replace ab OR bc
        System.out.println(s.replaceFirst("[a-z]", "(-)")); // replace any letter from a to z coming first, same with capital letters and digits
        System.out.println(s.replaceFirst("[a-zA-Z]", "(-)"));
        System.out.println("-".repeat(20));

        System.out.println(s.replaceFirst("[a-zA-z]*", "(-)"));
        System.out.println(s.replaceFirst("[a-z]+", "(-)"));
        System.out.println(s.replaceFirst("[a-z]*", "(-)"));    // Zero or more, so at starting of string it takes zero
        System.out.println(s.replaceFirst("[0-9]{2}", "(-)"));
        System.out.println("-".repeat(20));

        System.out.println(s.replaceFirst("[a-zA-z]*$", "(-)"));
        System.out.println(s.replaceFirst("^[a-zA-Z]{3}", "(-)"));
        System.out.println(s.replaceFirst("[aA]ny\\b", "(-)"));    // Word Boundary means word should end with Any or any
        System.out.println("-".repeat(20));

        String paragraph = """
                My name is Shreyas Shevale.
                I live in Kolhapur, Maharashtra.
                I am an electronics engineer.
                """;
        Scanner sc = new Scanner(paragraph);
        System.out.println(sc.delimiter());    // returns the patterns which scanner is using currently (Default - WhiteSpace \\s)
        sc.useDelimiter("\\R");
//        while (sc.hasNext()) {
//            System.out.println(sc.next());
//        }
//        sc.tokens()
//                .forEach(System.out::println);

//        sc.tokens()
//                .map(temp -> Arrays.stream(temp.split("\\R")).count())
//                .forEach(System.out::println);

        // Remove all punctuation from the paragraph
        sc.tokens()
                .map(temp -> temp.replaceAll("\\p{Punct}", ""))
                .forEach(System.out::println);
        sc.close();

    }
}
