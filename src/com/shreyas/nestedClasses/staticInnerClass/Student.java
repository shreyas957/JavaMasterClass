package com.shreyas.nestedClasses.staticInnerClass;

import java.util.Comparator;

public class Student {

    public static class StudentComparator<T extends Student> implements Comparator<Student> {
        private String sortType;

        public StudentComparator(String sortType) {
            this.sortType = sortType;
        }

        @Override
        public int compare(Student o1, Student o2) {
            if (sortType.equals("name")) {
                return o1.name.compareTo(o2.name);      // Sorting based on name.
            }

            return o1.id - o2.id;
        }
    }

    private int id;
    private String name;

    public Student() {
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
