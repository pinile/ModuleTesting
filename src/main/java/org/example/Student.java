package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ToString
public class Student {

    @Setter
    private StudentRepo studentRepo;

    @Getter
    @Setter
    private String name;

    private List<Integer> marks = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public List<Integer> getMarks() {
        return Collections.unmodifiableList(marks);
    }

    public void setMark(int mark) {
        if (mark < 2 || mark > 5) {
            throw new IllegalArgumentException("Mark must be between 2 and 5. Got: " + mark);
        }
        marks.add(mark);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.marks);
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
        return Objects.equals(this.name, other.name);
    }

    @SneakyThrows
    public int rating() {
        return studentRepo.getRatingForGradeSum(
                marks.stream()
                        .mapToInt(i -> i)
                        .sum()
        );
    }
}