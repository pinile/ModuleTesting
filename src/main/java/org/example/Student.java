package org.example;

import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Student {

  @Setter
  private StudentRepo studentRepo;

  @Getter
  @Setter
  private String name;
  @Getter
  private List<Integer> marks = new ArrayList<>();

  public Student(String name) {
    this.name = name;
  }

  public void setMark(int mark) {
    if (mark < 2 || mark > 5) {
      throw new IllegalArgumentException("Mark must be between 2 and 5. Got: " + mark);
    }
    marks.add(mark);
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