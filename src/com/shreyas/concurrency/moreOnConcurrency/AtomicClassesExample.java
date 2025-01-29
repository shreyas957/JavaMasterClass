package com.shreyas.concurrency.moreOnConcurrency;


import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

record Student(String name, int enrolledYear, int studentID) implements Comparable<Student> {

    @Override
    public int compareTo(Student o) {
        return this.studentID - o.studentID;
    }

}

class StudentId {
    private int id = 0;

    public int getId() {
        return id;
    }

    public synchronized int getNextId() {
        return ++id;  // not atomic operation
    }
}

class AtomicStudentId {
    private final AtomicInteger id = new AtomicInteger(0);

    public int getId() {
        return id.get();
    }

    public int getNextId() {
        return id.incrementAndGet();  // atomic operation
    }
}

public class AtomicClassesExample {
    private static Random random = new Random();
    private static ConcurrentSkipListSet<Student> studentSet = new ConcurrentSkipListSet<>();

    public static void main(String[] args) {
        AtomicStudentId idGenerator = new AtomicStudentId();
        Callable<Student> studentMaker = () -> {
            int studentID = idGenerator.getNextId();
            Student student = new Student("Shreyas " + studentID, random.nextInt(2003, 2025), studentID);
            studentSet.add(student);
            return student;
        };

        var executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            studentSet.clear();
            try {
                var futures = executor.invokeAll(
                        Collections.nCopies(10, studentMaker));
//                studentSet.forEach(System.out::println);
                System.out.println("Student Set Size: " + studentSet.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();

        // above code without any synchronization will not work as expected, there will be less student 8,9 than expected 10
        // It is happening because of non-atomic operation of idGenerator.getNextId()
        // if we synchronize the method getNextId() then it will work as expected

        // Another solution to this problem is to use Atomic classes like AtomicInteger (instead of int), AtomicLong, AtomicReference, etc.
    }
}
