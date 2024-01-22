package com.shreyas.nestedClasses.staticInnerClass;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(List.of(
                new Student(1, "Shreyas"),
                new Student(2, "Raj")));


        // passing comparator to the sort function of list which is inner static class
        students.sort(new Student.StudentComparator<>("name"));
        System.out.println(students);
        students.sort(new Student.StudentComparator<>("id"));
        System.out.println(students);

        // Instance of static inner class
        Student.StudentComparator<Student> comparator = new Student.StudentComparator<>("name");
        students.sort(comparator.reversed());
        System.out.println(students);
    }
}
