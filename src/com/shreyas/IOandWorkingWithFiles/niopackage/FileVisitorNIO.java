package com.shreyas.IOandWorkingWithFiles.niopackage;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class FileVisitorNIO {
    public static void main(String[] args) {
        Path path = Path.of("src");  // . represents the current directory

        // SimpleFileVisitor just return CONTINUE for every file and directory it encounters
        FileVisitor<Path> statsVisitor1 = new StatsVisitor1();
        try {
            Files.walkFileTree(path, statsVisitor1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("*****".repeat(20));

        FileVisitor<Path> statsVisitor2 = new StatsVisitor2(2);
        try {
            Files.walkFileTree(path, statsVisitor2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This class extends SimpleFileVisitor and overrides the visitFile, preVisitDirectory and postVisitDirectory methods.<br>
     * It is  used to print the file and directory names.<br>
     * Methods are called in the following order: preVisitDirectory, visitFile, postVisitDirectory <br>
     * {@code FileVisitResult} is used to control the flow of the walkFileTree method. <br>
     * It is enum with the following values: {@code CONTINUE, TERMINATE, SKIP_SIBLINGS, SKIP_SUBTREE}
     */
    private static class StatsVisitor1 extends SimpleFileVisitor<Path> {

        private int level;

        // This method only prints the file name i.e. it only visits the file.
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);
            System.out.println("\t".repeat(level) + file.getFileName());
            return FileVisitResult.CONTINUE;
        }

        // This method only prints the directory name i.e. it only visits the directory.
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);
            level++;
            System.out.println("\t".repeat(level + 1) + dir.getFileName());
            return FileVisitResult.CONTINUE;
        }

        // This method is called after the directory is visited. It is called after the visitFile method.
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Objects.requireNonNull(dir);
            if (exc != null)
                throw exc;
            level--;
            return FileVisitResult.CONTINUE;
        }
    }


    private static class StatsVisitor2 extends SimpleFileVisitor<Path> {

        // initialPath is the path of the directory that is passed to the walkFileTree method.
        private Path initialPath = null;

        // folderSize is used to store the directory and its size.
        private final Map<Path, Long> folderSize = new LinkedHashMap<>();

        // initialCount is the number of elements in the initialPath.
        private int initialCount;

        // printLevel is the level at which the directory and its size should be printed.
        private final int printLevel;

        public StatsVisitor2(int printLevel) {
            this.printLevel = printLevel;
        }

        // visitFile method is used to store the file size in the folderSize map.
        // merge method is used to add the size of the file to the existing size of the directory.
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);
            folderSize.merge(file.getParent(), 0L, (o, n) -> o += attrs.size());
            return FileVisitResult.CONTINUE;
        }

        // preVisitDirectory methode checks if the initialPath is null. If it is null, it initializes the initialPath and initialCount.
        // If it is not null, it initializes the folderSize for the directory.
        // relativeLevel means the level of the directory relative to the initialPath. i.e. if the initialPath is /a/b/c and the directory is /a/b/c/d/e, then the relativeLevel is 2.
        // If the relativeLevel is 1, then it clears the folderSize, because it is a new directory.
        // If the relativeLevel is not 1, then it initializes the folderSize for the directory.
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);
            if (initialPath == null) {
                initialPath = dir;
                initialCount = dir.getNameCount();
            } else {
                int relativeLevel = dir.getNameCount() - initialCount;
                if (relativeLevel == 1) {
                    folderSize.clear();
                }
                folderSize.put(dir, 0L);
            }
            return FileVisitResult.CONTINUE;
        }

        // postVisitDirectory method is used to print the directory and its size.
        // If the directory is the initialPath, then it returns TERMINATE, because we don't want to print the size of the initialPath.
        // If the relativeLevel is 1, which means it is a new directory, then it prints the directory and its size.
        // to print the info in a proper format, it uses the folderSize map and prints the directory and its size.
        // level is the level of the directory relative to the initialPath. if the initialPath is /a/b/c and the directory is /a/b/c/d/e, then the level is 2.
        //  print the directory and its size only if the level is less than printLevel.
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Objects.requireNonNull(dir);
            if (exc != null)
                throw exc;
            if (dir.equals(initialPath)) {
                return FileVisitResult.TERMINATE;
            }
            int relativeLevel = dir.getNameCount() - initialCount;
            if (relativeLevel == 1) {
                System.out.println("Folder: " + dir);
                folderSize.forEach((k, v) -> {
                    int level = k.getNameCount() - initialCount - 1;
                    if (level < printLevel) {
                        System.out.printf("%s[%s] - %,d bytes %n",
                                "\t".repeat(level), k.getFileName(), v);
                    }

                });
            } else {
                long size = folderSize.get(dir);
                folderSize.merge(dir.getParent(), size, (o, n) -> o += size);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
