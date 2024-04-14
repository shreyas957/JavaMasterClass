package com.shreyas.IOandWorkingWithFiles.readingfiles;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class demonstrates how to read a file using NIO in Java.
 */
public class ReadingWithNIO {
    public static void main(String[] args) {
        // Print the default file encoding and charset
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());
        System.out.println("*****".repeat(20));

        // Define the path of the file to be read
        Path path = Path.of("src/com/shreyas/IOandWorkingWithFiles/iopackage/fixedWidth.txt");

        try {
            // Read the entire file into a byte array and print it as a string
            System.out.println(new String(Files.readAllBytes(path)));
            System.out.println("-----".repeat(30));

            // Read the entire file into a string and print it
            System.out.println(Files.readString(path));  // readString() ultimately calls readAllBytes() internally with some additional checks
            System.out.println("-----".repeat(30));

            // Define a pattern to match each line of the file
            Pattern p = Pattern.compile("(.{15})(.{3})(.{12})(.{8})(.{2}).*");
            Set<String> values = new TreeSet<>();
            // Read all lines of the file and apply the pattern to each line
            Files.readAllLines(path).forEach(s -> {
                if (!s.startsWith("Name")) {
                    Matcher m = p.matcher(s);
                    if (m.matches()) {
                        // If the line matches the pattern, add the third group to the set
                        values.add(m.group(3).trim());
                    }
                }
            });
            // Print the set of values
            System.out.println(values);
            System.out.println("-----".repeat(30));

            // Read all lines of the file as a stream and apply the pattern to each line
            try (var StringStream = Files.lines(path)) {
                var result = StringStream
                        .skip(1)  // Skip the first line
                        .map(p::matcher)  // Apply the pattern to each line
                        .filter(Matcher::matches)  // Filter out lines that don't match the pattern
                        .map(m -> m.group(3).trim())  // Map each line to the third group of the pattern
                        .distinct()  // Remove duplicates
                        .sorted()  // Sort the results
                        .toArray(String[]::new);  // Convert the stream to an array
                // Print the array of results
                System.out.println(Arrays.toString(result));
            }
            System.out.println("-----".repeat(30));

            // Read all lines of the file as a stream and apply the pattern to each line
            try (var StringStream = Files.lines(path)) {
                var result = StringStream
                        .skip(1)  // Skip the first line
                        .map(p::matcher)  // Apply the pattern to each line
                        .filter(Matcher::matches)  // Filter out lines that don't match the pattern
                        .collect(Collectors.groupingBy(m -> m.group(3).trim(), Collectors.counting()));  // Group the lines by the third group of the pattern and count the occurrences
                // Print the map of results
                System.out.println(result);
            }
        } catch (IOException e) {
            // If an IO exception occurs, wrap it in a runtime exception and throw it
            throw new RuntimeException(e);
        }
    }
}