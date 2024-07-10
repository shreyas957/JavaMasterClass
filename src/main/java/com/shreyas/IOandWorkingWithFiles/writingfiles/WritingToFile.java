package com.shreyas.IOandWorkingWithFiles.writingfiles;

import com.shreyas.IOandWorkingWithFiles.writingfiles.student.Course;
import com.shreyas.IOandWorkingWithFiles.writingfiles.student.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WritingToFile {
    public static void main(String[] args) {
        String header = """
                Student Id,Country Code,Enrolled Year,Age,Gender,\
                Experienced,Course Code,Engagement Month,Engagement Year,\
                Engagement Type""";

        Course jmc = new Course("JMC", "Java Masterclass");
        Course pymc = new Course("PYC", "Python Masterclass");
        List<Student> students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(5)
                .toList();

        System.out.println(header);
        students.forEach(s -> s.getEngagementRecords().forEach(System.out::println));

        Path path = Path.of("src/com/shreyas/IOandWorkingWithFiles/writingfiles/students.csv");
        try {
            Files.writeString(path, header);
            for (Student student : students) {
                Files.write(path, student.getEngagementRecords(),
                        StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            List<String> data = new ArrayList<>();
            data.add(header);
            for (Student student : students) {
                data.addAll(student.getEngagementRecords());
            }
            Files.write(path, data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (BufferedWriter writer =
                     Files.newBufferedWriter(Path.of("src/com/shreyas/IOandWorkingWithFiles/writingfiles/take2.csv"))) {
            writer.write(header);
            writer.newLine();
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.write(record);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (FileWriter writer = new FileWriter("src/com/shreyas/IOandWorkingWithFiles/writingfiles/take3.csv")) {
            writer.write(header);
            writer.write("\n");
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.write(record);
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter("src/com/shreyas/IOandWorkingWithFiles/writingfiles/take4.csv")) {
//            writer.print(header);
            writer.println(header);
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.println(record);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
