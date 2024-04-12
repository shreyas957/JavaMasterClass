package com.shreyas.IOandWorkingWithFiles.iopackage;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadingFileWithIO {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("src/com/shreyas/IOandWorkingWithFiles/iopackage/ReadThisFile.txt")) {
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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/com/shreyas/IOandWorkingWithFiles/iopackage/ReadThisFile.txt"))) {
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//            }
            bufferedReader.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
