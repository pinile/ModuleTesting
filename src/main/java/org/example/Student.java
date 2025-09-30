package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ToString
public class Student {

    private final List<Integer> grades = new ArrayList<>();
    @Setter
    private StudentRepo studentRepo;
    @Getter
    @Setter
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public List<Integer> getGrades() {
        return Collections.unmodifiableList(grades);
    }

    public void setGrade(int grade) {
        if (grade < 2 || grade > 5) {
            throw new IllegalArgumentException("Mark must be between 2 and 5. Got: " + grade);
        }
        grades.add(grade);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.grades);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final Student other = (Student) o;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.grades, other.grades);
    }

    public int rating() {
        if (studentRepo == null) {
            throw new IllegalStateException("Student repo is null");
        } else {
            return studentRepo.getRatingForGradeSum(
                    grades.stream()
                            .mapToInt(i -> i)
                            .sum()
            );
        }
    }
}