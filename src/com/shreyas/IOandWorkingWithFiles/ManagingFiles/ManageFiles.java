package com.shreyas.IOandWorkingWithFiles.ManagingFiles;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * This class is used to manage files and directories.
 * It includes operations like renaming, moving and copying files and directories.
 */
public class ManageFiles {
    public static void main(String[] args) {
        // Create File objects for the original and new file names
        File file = new File("src/com/shreyas/IOandWorkingWithFiles/ManagingFiles/test.txt");
        File newName = new File("src/com/shreyas/IOandWorkingWithFiles/ManagingFiles/test-updated.txt");

        // Check if the original file exists, if so, rename it
        if (file.exists()) {
            boolean b = file.renameTo(newName);
            System.out.println(b);
            System.out.println("File renamed successfully");
        }

        // Create Path objects for the original and new file names
        Path path1 = file.toPath();
        Path path2 = newName.toPath();

        // Try to move the file to the new path
        try {
            Files.move(path2, path1);
            System.out.println("Path renamed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create Path objects for moving a file to a new directory
        Path path3 = Path.of("src/com/shreyas/IOandWorkingWithFiles/ManagingFiles/test1.txt");
        Path path4 = Path.of("src/com/shreyas/IOandWorkingWithFiles/ManagingFiles/example/test1.txt");

        // Try to move the file to the new directory, creating the directory if it doesn't exist
        try {
            Files.createDirectories(path4.subpath(0, path4.getNameCount() - 1)); // Create directories if not present and then move
            // getNameCount() returns the number of elements in the path
            Files.move(path3, path4);
            System.out.println("Path moved (renamed to) successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create Path objects for moving a directory
        Path path5 = Path.of("src/com/shreyas/IOandWorkingWithFiles/ManagingFiles/example");
        Path path6 = Path.of("src/com/shreyas/IOandWorkingWithFiles/ManagingFiles/resource");

        // Try to move the directory
        try {
            Files.move(path5, path6);
            System.out.println("Directory moved (renamed to) successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a Path object for the directory to be copied
        Path path7 = Path.of("src/com/shreyas/IOandWorkingWithFiles/ManagingFiles/copiedResource");

        // Try to copy the directory
        try {
            recurseDelete(path7);
            recurseCopy(path6, path7);
            System.out.println("Directory copied successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a BufferedReader and PrintWriter to copy the contents of a file
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/com/shreyas/IOandWorkingWithFiles/ManagingFiles/copiedResource/test1.txt"));
             PrintWriter writer = new PrintWriter("src/com/shreyas/IOandWorkingWithFiles/ManagingFiles/copiedResource/test2.txt")) {
            reader.transferTo(writer); // Transfer the contents of the reader to the writer
            // Useful when large files are to be copied over network storage or to a different location
            System.out.println("DONE");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create a URI object from a URL string
        String url = "http://spring-boot-full-stack-professio-env.eba-zwt8muqa.ap-south-1.elasticbeanstalk.com:8080/ping";
        URI uri = URI.create(url);

        // Create an InputStreamReader and BufferedWriter to copy the contents of a URL to a file
        try (var reader = new InputStreamReader(uri.toURL().openStream());
             BufferedWriter writer = Files.newBufferedWriter(Path.of("src/com/shreyas/IOandWorkingWithFiles/ManagingFiles/resource/UrlData.txt"))) {
            reader.transferTo(writer); // Transfer the contents of the reader to the writer
            // Useful when large files are to be copied over network storage or to a different location
            System.out.println("DONE");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create an InputStreamReader and PrintWriter to copy the contents of a URL to a file, with some modifications
        try (var reader = new InputStreamReader(uri.toURL().openStream());
             PrintWriter writer = new PrintWriter("src/com/shreyas/IOandWorkingWithFiles/ManagingFiles/resource/UrlData2.csv")) {
            reader.transferTo(new Writer() {
                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {
                    String jsonString = new String(cbuf, off, len).trim();
                    jsonString = jsonString.replace("{", "");
                    jsonString = jsonString.replaceAll("}", "");
                    writer.write(jsonString);
                }

                @Override
                public void flush() throws IOException {
                    writer.flush();
                }

                @Override
                public void close() throws IOException {
                    writer.close();
                }
            }); // Transfer the contents of the reader to the writer
            // Useful when large files are to be copied over network storage or to a different location
            System.out.println("DONE");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to recursively copy a directory and its contents.
     * It performs a shallow copy of the directory, meaning it only copies the directory and not its contents.
     *
     * @param source The source directory to be copied.
     * @param target The target directory where the source directory will be copied.
     * @throws IOException If an I/O error occurs.
     */
    private static void recurseCopy(Path source, Path target) throws IOException {
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);  // Make shallow copy
        if (Files.isDirectory(source)) {
            try (var entries = Files.list(source)) {
                entries.toList().forEach(
                        e -> {
                            try {
                                recurseCopy(e, target.resolve(e.getFileName()));
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                );
            }
        }
    }

    /**
     * This method is used to recursively delete a directory and its contents.
     * It checks if the path is a directory, if so, it lists all the entries in the directory and recursively calls the method on each entry.
     * After all entries in a directory are deleted, it deletes the directory itself.
     * If the path is a file, it directly deletes the file.
     * If the file or directory does not exist, it does nothing.
     *
     * @param source The path of the directory or file to be deleted.
     * @throws IOException If an I/O error occurs.
     */
    private static void recurseDelete(Path source) throws IOException {
        // Check if the path is a directory
        if (Files.isDirectory(source)) {
            // List all entries in the directory
            try (var entries = Files.list(source)) {
                // For each entry, recursively call the method
                entries.toList().forEach(
                        e -> {
                            try {
                                recurseDelete(e);
                            } catch (IOException ex) {
                                // If an I/O error occurs, throw a runtime exception
                                throw new RuntimeException(ex);
                            }
                        }
                );
            }
        }
        // Delete the file or directory
        Files.deleteIfExists(source);
    }
}