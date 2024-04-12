package com.shreyas.basics;

import java.util.Scanner;

/**
 * The Java Scanner class provides nextXXX() methods to return the type of value such as nextInt(), nextByte(),
 * nextShort(), next(), nextLine(), nextDouble(), nextFloat(), nextBoolean(), etc. To get a single character from the scanner,
 * you can call next().charAt(0) method which returns a single character.
 */
public class UserInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);        // Creating instance/Object of Scanner class

        System.out.println("Hi, Whats your name?");

        String name = sc.nextLine();       // Using that instance/Object for taking input.

        System.out.println("Hi " + name + "!" + " What is your age?");
        int age = sc.nextInt();
        System.out.println("Whoa!! you are " + (age - 1) + " plus.");
        sc.close();

        // Below when we pass String as a constructor argument to Scanner class,
        // It will parse the string for specific types of patterns.
        String input = "Hello, I am 25 years old";
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                System.out.println(scanner.nextInt());
            } else {
                scanner.next();
            }
        }
        scanner.close();

        String csvLine = "John Doe,25,Los Angeles,Software Engineer";
        Scanner scanner2 = new Scanner(csvLine);
        scanner.useDelimiter(",");

        while (scanner2.hasNext()) {
            System.out.println(scanner2.next());
        }

        scanner2.close();
    }
}
