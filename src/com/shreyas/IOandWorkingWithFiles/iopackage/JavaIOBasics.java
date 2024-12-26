package com.shreyas.IOandWorkingWithFiles.iopackage;

import java.io.File;

public class JavaIOBasics {
    public static void main(String[] args) {
        File file = new File("src/com/shreyas/IOandWorkingWithFiles/iopackage/example.txt");

        if (!file.exists()) {
            System.out.println("File does not exist!");
        } else {
            System.out.println("File exists!");
        }
        System.out.println("File path: " + file.getAbsolutePath());

        for (File f : File.listRoots()) {
            System.out.println(f);
        }

        File file1 = new File("/File/example.txt"); // / means root directory
        System.out.println("File path: " + file1.getAbsolutePath());

        File file2 = new File("/", "File/example.txt");     // / means root directory
        File file3 = new File(".", "File/example.txt");     // . means current directory
        File file4 = new File("..", "File/example.txt");     // .. means parent directory

        // S:\My learning projects\JavaMasterClass\.\File\example.txt here in this path . means redundant name element and
        // can be removed using normalize() method.
        System.out.println("File path: " + file2.getAbsolutePath());
        System.out.println("File path: " + file3.getAbsolutePath());
        System.out.println("File path: " + file4.getAbsolutePath());
        System.out.println("-".repeat(20));

        File file5 = new File(new File(""), "example.txt");
        System.out.println("File path: " + file5.getAbsolutePath());
        System.out.println("-".repeat(20));

        createFile("src/com/shreyas/IOandWorkingWithFiles/iopackage/example2.txt");
    }

    // Create static method which returns void and takes filename as argument and then will create 'File' instance from name which belows functions.
    // if file exists then delete it and again make new one
    // if file does not exist then create new file
    // then check if file can be read, written and executed.
    public static void createFile(String filename) {
        File file = new File(filename);
        boolean exists = file.exists();
        if (exists) {
            boolean delete = file.delete();
            System.out.println("File deleted successfully!");
            exists = file.exists();
        }

        if (!exists) {
            try {
                boolean newFile = file.createNewFile();
                if (newFile) {
                    System.out.println("File created successfully!");
                } else {
                    System.out.println("File already exists!");
                }
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }

        if (file.canRead()) {
            System.out.println("File can be read!");
        }
        if (file.canWrite()) {
            System.out.println("File can be written!");
        }
        if (file.canExecute()) {
            System.out.println("File can be executed!");
        }
    }
}
