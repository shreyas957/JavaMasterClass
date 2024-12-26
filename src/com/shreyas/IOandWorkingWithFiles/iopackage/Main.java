package com.shreyas.IOandWorkingWithFiles.iopackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = createFile("src/com/java/workingWithFiles/example.txt");

        writeToFile(file, false);
        readFromFile(file);
    }

    private static File createFile(String path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                boolean newFile = file.createNewFile();// To delete the file we have ".delete()" method.
            }
            return file;
        } catch (IOException e) {
            System.out.println("No such file or directory exists!");
            throw new IllegalStateException(e);
        }
    }

    private static void writeToFile(File file, boolean append) {
        // try with resources -->   By using this we don't need to flush and close the file manually, So we don't need to do that every time.
        // This is done because if we have any class which implements "closeable" and "flushable",
        // And thus the process is done automatically --> In this case PrintWriter implements them.

        try (
                FileWriter fileWriter = new FileWriter(file, append);
                PrintWriter writer = new PrintWriter(fileWriter);
        ) {
            writer.println("Writing to te file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

//        try {
//            // Used to write into the file
//            // FileWriter fileWriter1 = new FileWriter(file);   --> Override any previous contents and write from the beginning, i.e. false
//            FileWriter fileWriter = new FileWriter(file, append);
//            PrintWriter writer = new PrintWriter(fileWriter);
//            writer.println("Hello ");
//
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }

    private static void readFromFile(File file) {
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                System.out.println(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
