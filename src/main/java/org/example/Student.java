package org.example;

import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Student {

  @Getter
  @Setter
  private String name;
  private List<Integer> marks = new ArrayList<>();

  public Student(String name) {
    this.name = name;
  }

  public List<Integer> getMarks() {
    return marks;
  }

  public void setMark(int mark) {
    if (mark < 2 || mark > 5) {
      throw new IllegalArgumentException("mark must be between 2 and 5. Get: " + mark);
    }
    marks.add(mark);
  }
}