package com.shreyas.IOandWorkingWithFiles.niopackage;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Main class to start the file tree walk.
 */
public class FileWalkChallenge {
    public static void main(String[] args) {
        // Define the root path for the file tree walk
        var path = Path.of("src/com/shreyas");
        try {
            // Create a new StatsVisitor with a print level of 2
            FileVisitor<Path> fileVisitor = new StatsVisitor(2);
            // Start the file tree walk
            Files.walkFileTree(path, fileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A FileVisitor implementation that collects statistics about the directories and files it visits.
     */
    private static class StatsVisitor implements FileVisitor<Path> {

        // The initial path of the file tree walk
        private Path initialPath = null;

        // A map to store the statistics for each directory. Key is the directory path, value is a map with statistics.
        private final Map<Path, Map<String, Long>> folderSize = new LinkedHashMap<>();

        // The number of elements in the initial path
        private int initialCount;

        // The depth of directories to print information about
        private final int printLevel;

        // Constants used as keys in the folderSize map
        private static final String DIR_CNT = "dirCount";
        private static final String FILE_CNT = "FileCount";
        private static final String FILE_SIZE = "fileSize";

        /**
         * Constructor for StatsVisitor.
         *
         * @param printLevel The depth of directories to print information about
         */
        public StatsVisitor(int printLevel) {
            this.printLevel = printLevel;
        }

        /**
         * Called for each file visited.
         *
         * @param file  The file being visited
         * @param attrs The attributes of the file
         * @return CONTINUE to continue the file tree walk, or TERMINATE to stop it
         * @throws IOException If an I/O error occurs
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);

            // Get the statistics map for the parent directory
            var parentMap = folderSize.get(file.getParent());
            if (parentMap != null) {
                // Update the file size and file count for the parent directory
                long fileSize = attrs.size();
                parentMap.merge(FILE_SIZE, fileSize, Long::sum);
                parentMap.merge(FILE_CNT, 1L, Long::sum);
            }
            return FileVisitResult.CONTINUE;
        }

        /**
         * Called when a file cannot be visited.
         *
         * @param file The file that could not be visited
         * @param exc  The I/O exception that was thrown when trying to visit the file
         * @return CONTINUE to continue the file tree walk, or TERMINATE to stop it
         * @throws IOException If an I/O error occurs
         */
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            Objects.requireNonNull(file);
            if (exc != null)
                System.out.println(exc.getClass().getSimpleName() + " " + file);
            return FileVisitResult.CONTINUE;
        }

        /**
         * Called before a directory's entries are visited.
         *
         * @param dir   The directory being visited
         * @param attrs The attributes of the directory
         * @return CONTINUE to continue the file tree walk, or TERMINATE to stop it
         * @throws IOException If an I/O error occurs
         */
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);

            // Initialize initialPath and initialCount, or add the directory to the folderSize map
            if (initialPath == null) {
                initialPath = dir;
                initialCount = dir.getNameCount();
            } else {
                int relativeLevel = dir.getNameCount() - initialCount;
                // Clear the folderSize map if the directory is at the same level as the initial path
                if (relativeLevel == 1) {
                    folderSize.clear();
                }
                folderSize.put(dir, new HashMap<>());
            }
            return FileVisitResult.CONTINUE;
        }

        /**
         * Called after a directory's entries are visited.
         *
         * @param dir The directory that was visited
         * @param exc The I/O exception that was thrown when trying to visit the directory, or null if no exception was thrown
         * @return CONTINUE to continue the file tree walk, or TERMINATE to stop it
         * @throws IOException If an I/O error occurs
         */
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Objects.requireNonNull(dir);
            if (exc != null)
                throw exc;
            if (dir.equals(initialPath)) {
                return FileVisitResult.TERMINATE;
            }
            int relativeLevel = dir.getNameCount() - initialCount;
            // Print the directory's information if its level is 1. relativeLevel is 1 for the initial path.
            if (relativeLevel == 1) {
                // Print the directory's information if its level is less than printLevel
                folderSize.forEach((k, v) -> {
                    int level = k.getNameCount() - initialCount - 1;
                    if (level < printLevel) {
                        long size = v.getOrDefault(FILE_SIZE, 0L);
                        System.out.printf("%s[%s] - %,d bytes, %d files, %d folders %n",
                                "\t".repeat(level), k.getFileName(), size,
                                v.getOrDefault(FILE_CNT, 0L),
                                v.getOrDefault(DIR_CNT, 0L));
                    }

                });
            } else {
                // Update the parent directory's statistics with the directory's statistics
                // Below we first get the parent directory's statistics and then the child directory's statistics
                // We then update the parent directory's statistics with the child directory's statistics
                var parentMap = folderSize.get(dir.getParent());
                var childMap = folderSize.get(dir);
                long folderCount = childMap.getOrDefault(DIR_CNT, 0L);
                long fileSize = childMap.getOrDefault(FILE_SIZE, 0L);
                long fileCount = childMap.getOrDefault(FILE_CNT, 0L);
                parentMap.merge(DIR_CNT, folderCount + 1, Long::sum);
                parentMap.merge(FILE_SIZE, fileSize, Long::sum);
                parentMap.merge(FILE_CNT, fileCount, Long::sum);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}


/**
 * Example:
 * <p>
 * Consider a simple file structure as an example:
 * <p>
 * root_folder
 * │   file1.txt (size: 10 bytes)
 * │   file2.txt (size: 20 bytes)
 * │
 * └───subfolder1
 * │   file3.txt (size: 30 bytes)
 * │   file4.txt (size: 40 bytes)
 * │
 * └───subfolder2
 * │   file5.txt (size: 50 bytes)
 * │   file6.txt (size: 60 bytes)
 * <p>
 * When the `walkFileTree` method starts, it begins at the `root_folder`. It first visits `file1.txt` and `file2.txt`,
 * and the `visitFile` method adds their sizes to the total size of `root_folder` in the `folderSize` map.
 * So, the size of `root_folder` is now 30 bytes.
 * <p>
 * Next, it visits `subfolder1`. The `preVisitDirectory` method adds an entry for `subfolder1` in the `folderSize` map
 * with a size of 0. Then it visits `file3.txt` and `file4.txt`, and the `visitFile` method adds their sizes to the total
 * size of `subfolder1` in the `folderSize` map. So, the size of `subfolder1` is now 70 bytes.
 * <p>
 * Next, it visits `subfolder2`. The `preVisitDirectory` method adds an entry for `subfolder2` in the `folderSize` map
 * with a size of 0. Then it visits `file5.txt` and `file6.txt`, and the `visitFile` method adds their sizes to the total
 * size of `subfolder2` in the `folderSize` map. So, the size of `subfolder2` is now 110 bytes.
 * <p>
 * When it finishes visiting `subfolder2`, the `postVisitDirectory` method adds the size of `subfolder2` to the size of
 * its parent directory (`subfolder1`) in the `folderSize` map. So, the size of `subfolder1` is now 180 bytes.
 * <p>
 * When it finishes visiting `subfolder1`, the `postVisitDirectory` method adds the size of `subfolder1` to the size of
 * its parent directory (`root_folder`) in the `folderSize` map. So, the size of `root_folder` is now 210 bytes.
 * <p>
 * This way, when the `walkFileTree` method finishes, the `folderSize` map contains the total size of each directory,
 * including the sizes of all the files and subdirectories within each directory. This is why the `postVisitDirectory`
 * method's else block updates the parent directory's statistics with the child directory's statistics.
 */


