package com.shreyas.IOandWorkingWithFiles.niopackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Stream;


public class JavaNIO {
    public static void main(String[] args) throws IOException {

        usePath("src/com/shreyas/IOandWorkingWithFiles/niopackage/example2.txt");
        System.out.println("*****".repeat(20));

        Path path = Path.of("src/com/shreyas/IOandWorkingWithFiles/niopackage/example2.txt");
        pathInfo(path);
        System.out.println("*****".repeat(20));

        Path path1 = Path.of("src/com/shreyas/IOandWorkingWithFiles/niopackage/testing.test/logs.txt");
        logStatement(path1);
        System.out.println("*****".repeat(20));

        extraInfo(path1);
        System.out.println("*****".repeat(20));

        otherUsefulMethod(Path.of(""));  // empty String path represents the current directory, dot (.) also represents the current directory.
    }

    private static void usePath(String filePath) throws IOException {
        Path path = Path.of(filePath);
//        Path path = Paths.get("src/com/shreyas/IOandWorkingWithFiles/niopackage/example.txt");
        System.out.println(Files.exists(path) ? "File exists!" : "File does not exist!");

        Files.deleteIfExists(path);

        boolean exists = Files.exists(path);

        if (!exists) {
            Files.createFile(path);
            System.out.println("File created successfully!");
        }

        boolean readable = Files.isReadable(path);
        boolean writable = Files.isWritable(path);
        boolean executable = Files.isExecutable(path);

        if (writable) {
            System.out.println("File is writable!");
            Files.writeString(path, """
                    Hello, this is a new file created using Java NIO.
                    Owner of this file is Shreyas.
                    Happy learning!
                    """);
        }
        if (readable) {
            System.out.println("File is readable!");
            Files.readAllLines(path).forEach(System.out::println);

        }
        if (executable) {
            System.out.println("File is executable!");
        }
    }

    private static void pathInfo(Path path) {
        System.out.println("File name: " + path.getFileName());

        System.out.println("Root directory: " + path.getRoot()); // getRoot() method returns the root directory of the file.
        System.out.println("Parent directory: " + path.getParent()); // getParent() method returns the parent directory of the file. (Only directories)
        System.out.println("-".repeat(20));

        Path absolutePath = path.toAbsolutePath();
        System.out.println("Absolute path: " + absolutePath);
        System.out.println("Root directory: " + absolutePath.getRoot());
        System.out.println("Parent directory: " + absolutePath.getParent());
        System.out.println("-".repeat(20));

        System.out.println("path isAbsolute? " + path.isAbsolute());
        System.out.println("-".repeat(20));

//        int j = 0;
//        var it = path.toAbsolutePath().iterator();
//        while (it.hasNext()) {
//            System.out.println(".".repeat(j++) + " " + it.next());
//            j++;
//        }

        int count = absolutePath.getNameCount();  // getNameCount() method returns the number of elements in the path. which is 1 + number of directories in the path.
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            System.out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));
        }

    }

    private static void logStatement(Path path) {
        try {
            Path parent = path.getParent();
            // getParent() method returns the parent directory of the file. (Only directories)
            System.out.println("Path :" + path);
            System.out.println("Parent :" + parent);
            if (!Files.exists(parent)) {
                // below method creates the directory if it does not exist.
//                Files.createDirectory(parent);
                Files.createDirectories(parent);
            }
            // Below writeString methods writes the string to the file and creates the file if it does not exist.
            // also it has the option to append the text to the file.
            Files.writeString(
                    path,
                    Instant.now() + ": Hello, this is a new file created using Java NIO.\n",
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extraInfo(Path path) {
        try {
            System.out.println(Files.getAttribute(path, "creationTime"));
            Map<String, Object> attributes = Files.readAttributes(path, "*"); // returns map of attributes of the file.
            attributes.entrySet().forEach(System.out::println);
            System.out.println(Files.probeContentType(path)); // returns the content type of the file.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void otherUsefulMethod(Path path) {
        System.out.println(path.toAbsolutePath());

        // Below method lists all the files & sub-folders of the given directory/path.
        try (Stream<Path> pathStream = Files.list(path)) {
            pathStream
                    .map(JavaNIO::ListDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-".repeat(20));

        // Below method lists all the files & sub-folders of the given directory/path recursively one by one
        try (Stream<Path> pathStream = Files.walk(path)) {
            pathStream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-".repeat(20));

        // Below method lists all the files & sub-folders of the given directory/path recursively upto the given depth.
        // depth means how many levels of sub-folders to go inside.
        // isRegularFile() method simply means it filters only files and not the directories.
        try (Stream<Path> pathStream = Files.walk(path, 2)) {
            pathStream
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-".repeat(20));

        // Below method lists all the files of the given directory/path recursively upto the given depth.
        // Also, it filters the files based on the given condition.
        // Here, it filters the only files with based on the size of the file.
        // Matcher which is 3rd argument is a BiPredicate which takes 2 arguments, Path and BasicFileAttributes.
        try (Stream<Path> pathStream = Files.find(path, Integer.MAX_VALUE, (p, attr) -> attr.isRegularFile() && attr.size() > 10000)) {
            pathStream
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-".repeat(20));

        // Directory Stream provides iterator to iterate over the files and sub-folders.
        // It is similar to Files.list() method but it is more efficient.
        try (var directoryStream = Files.newDirectoryStream(path)) {
            directoryStream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-".repeat(20));

        Path path1 = path.resolve(".idea");
        // It also has one argument called 'String globe' pattern to filter the files based on the given condition.
        try (var directoryStream = Files.newDirectoryStream(path1, "*.xml")) {
            directoryStream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-".repeat(20));

        // Here in this overloaded version of method we can pass the Predicate to filter the files based on the given condition.
        try (var directoryStream = Files.newDirectoryStream(path1,
                p -> p.getFileName().toString().endsWith(".xml") && Files.isRegularFile(p) && Files.size(p) > 300)) {
            directoryStream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-".repeat(20));


    }

    private static String ListDir(Path path) {
        try {
            boolean isDir = Files.isDirectory(path);
            FileTime time = Files.getLastModifiedTime(path);
            return String.format("%s %-15s %s", isDir ? "<DIR>" : "<FILE>", time, path);
        } catch (IOException e) {
            System.out.println("Something went wrong!");
            return path.toString();
        }
    }
}