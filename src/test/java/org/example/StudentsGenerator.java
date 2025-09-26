package org.example;

import java.util.List;

public class StudentsGenerator {
    public static List<Student> generateStudents() {
        return List.of(
                new Student("vasya"),
                new Student("pete"),
                new Student("masha"),
                new Student("pasha")
        );
    }
}
