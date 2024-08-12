package com.shreyas.IOandWorkingWithFiles.ManagingFiles;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RandomAccessFileExample {

    private static final Map<Long, Long> indexIds = new LinkedHashMap<>();
    private static int recordsInFile = 0;

    static {
        try (RandomAccessFile rb = new RandomAccessFile("student.idx", "r")) {
            loadIndex(rb, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        BuildStudentData.build("student", true);
        try (RandomAccessFile ra = new RandomAccessFile("student.dat", "r")) {
//            loadIndex(ra, 0);  --> Using static initializer instead
            System.out.println("Enter a Student Id or 0 to exit: ");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                long studentId = scanner.nextLong();
                if (studentId < 1) {
                    break;
                }
                ra.seek(indexIds.get(studentId));
                String record = ra.readUTF();
                System.out.println(record);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadIndex(RandomAccessFile ra, int indexPosition) {
        try {
            ra.seek(indexPosition);
            recordsInFile = ra.readInt();
            System.out.println("Records in file: " + recordsInFile);
            for (int i = 0; i < recordsInFile; i++) {
                indexIds.put(ra.readLong(), ra.readLong());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class BuildStudentData {
    public static void build(String datFileName, boolean separateIndexFile) {
        Path studentJson = Path.of("src/main/java/com/shreyas/IOandWorkingWithFiles/ManagingFiles/students.json");
        String dataFile = datFileName + ".dat";
        Map<Long, Long> indexIds = new LinkedHashMap<>();

        try {
            Files.deleteIfExists(Path.of(dataFile));
            String data = Files.readString(studentJson);
            data = data.replaceFirst("^(\\[)", "")
                    .replaceFirst("(])$", "");
            var records = data.split(System.lineSeparator());
            System.out.println("# of records: " + records.length);

            long startingPos = separateIndexFile ? 0 : 4 + (16l * records.length);

            Pattern idPattern = Pattern.compile("studentId\":([0-9]+)");
            try (RandomAccessFile raf = new RandomAccessFile(dataFile, "rw")) {
                raf.seek(startingPos);
                for (String record : records) {
                    Matcher m = idPattern.matcher(record);
                    if (m.find()) {
                        long id = Long.parseLong(m.group(1));
                        indexIds.put(id, raf.getFilePointer());
                        raf.writeUTF(record);
                    }
                }
                writeIndex((separateIndexFile ? new RandomAccessFile(datFileName + ".idx", "rw") : raf), indexIds);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeIndex(RandomAccessFile ra, Map<Long, Long> indexMap) {
        try {
            ra.seek(0);
            ra.writeInt(indexMap.size());
            for (var studentId : indexMap.entrySet()) {
                ra.writeLong(studentId.getKey());
                ra.writeLong(studentId.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
