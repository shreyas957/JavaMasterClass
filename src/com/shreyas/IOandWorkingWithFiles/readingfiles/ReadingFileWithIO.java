package com.shreyas.IOandWorkingWithFiles.readingfiles;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class ReadingFileWithIO {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("src/com/shreyas/IOandWorkingWithFiles/readingfiles/ReadThisFile.txt")) {
            // FileReader reads the file character by character
            // the character array is used to read the file in chunks
            char[] buffer = new char[1000];
            // cnt is the number of characters read
            int cnt;
            // read() returns -1 when the end of the file is reached
            while ((cnt = reader.read(buffer)) != -1) {
                // Converting the character array to string
                String str = new String(buffer, 0, cnt);
                System.out.print(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n" + "******".repeat(20));

        // Using BufferedReader to read the file line by line
        // FileReader reads one character at a time directly from the file, which can be slow for large files.
        // BufferedReader uses a buffer to read chunks of characters at a time, reducing the number of I/O operations.
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/com/shreyas/IOandWorkingWithFiles/readingfiles/ReadThisFile.txt"))) {
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//            }
            bufferedReader.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n" + "******".repeat(20));

        try (FileInputStream fileInputStream =
                     new FileInputStream("src/com/shreyas/IOandWorkingWithFiles/readingfiles/ReadThisFile.txt")) {
            fileInputStream.transferTo(System.out);  // This outputs content of the file to the console

            // read in chunks of 1000 bytes
            byte[] buffer = new byte[1000];
            int cnt;
            while ((cnt = fileInputStream.read(buffer)) != -1) {
                String str = new String(buffer, 0, cnt);
                System.out.print(str);
            }

            int read = fileInputStream.read();  // return one byte read from the file
            System.out.println((char) read);  // convert the byte to char and print it
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
