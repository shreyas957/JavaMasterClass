package com.shreyas.IOandWorkingWithFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileExceptions {
    public static void main(String[] args) {
        String fileName = "testing.csv";
        Path path = Paths.get(fileName);
        // EAFP --> Easier to ask forgiveness than permission
        try {
            List<String> lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        File file = new File(fileName);
        if(!file.exists()) {    // LBYL --> Look before you leap
            System.out.println("File doesnt exist");
        }

        // Try with resources
        try(FileReader fileReader = new FileReader(fileName)) {

        } catch (FileNotFoundException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // We cant catch FileNotFoundException after IOException because IOException includes,
        // it's all subclass's too which is FileNotFoundException.

    }
}
