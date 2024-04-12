package com.shreyas.IOandWorkingWithFiles.iopackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * This class demonstrates various use cases of the Scanner class in Java.
 */
public class ScannerForReadingFiles {
    public static void main(String[] args) {
        // Reading a file line by line using Scanner
        try (Scanner scanner = new Scanner(new File("src/com/shreyas/IOandWorkingWithFiles/iopackage/ReadThisFile.txt"))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
            // Printing the current delimiter used by the Scanner
            System.out.println(scanner.delimiter());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-".repeat(30));

        // Reading the entire file content at once using Scanner
        try (Scanner scanner = new Scanner(new File("src/com/shreyas/IOandWorkingWithFiles/iopackage/ReadThisFile.txt"))) {
            scanner.useDelimiter("$"); // $ is the end of the file
            scanner.tokens().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-".repeat(30));

        // Using Scanner to find all words with 10 or more characters in a file
        try (Scanner scanner = new Scanner(new File("src/com/shreyas/IOandWorkingWithFiles/iopackage/ReadThisFile.txt"))) {
            scanner.findAll("[A-Za-z]{10,}")
                    .map(MatchResult::group)
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-".repeat(30));

        // Using Scanner to read a fixed width file
        try (Scanner scanner = new Scanner(new File("src/com/shreyas/IOandWorkingWithFiles/iopackage/fixedWidth.txt"))) {
            // Below regex is for fixed width file, It will read 15 characters, then 3 characters, then 12 characters, then 8 characters, then 2 characters
            // and then anything else
            var result = scanner.findAll("(.{15})(.{3})(.{12})(.{8})(.{2}).*")
                    .skip(1) // Skip the header
                    .map(mr -> mr.group(5))
                    .distinct()
                    .sorted()
                    .toArray(String[]::new);
            System.out.println(Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("-".repeat(30));

        // Reading a file line by line using Scanner with Path
        try (Scanner scanner = new Scanner(Path.of("src/com/shreyas/IOandWorkingWithFiles/iopackage/ReadThisFile.txt"))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Note: We can also pass FileReaders, InputStreams, etc to the Scanner constructor
    }
}