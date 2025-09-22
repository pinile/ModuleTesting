package org.example;

import java.util.List;

public class Tests {

  public void marksInRange() {
    Student student = new Student("vasya");
    List<Integer> lst = List.of(2, 3, 4, 5);
    student.setMark(lst.get(0));
    student.setMark(lst.get(1));
    student.setMark(lst.get(2));
    student.setMark(lst.get(3));
    if (!student.getMarks().equals(lst)) {
      throw new RuntimeException("Test error");
    }
  }

  public void marksNotInRange() {
    Student student = new Student("vasya_pupkin");
    List<Integer> lst = List.of(1, 0, 6, 99);

    try {
      student.setMark(lst.get(0));
      student.setMark(lst.get(1));
      student.setMark(lst.get(2));
      student.setMark(lst.get(3));
    } catch (IllegalArgumentException e) {
      return;
    }
    throw new RuntimeException("Test error");
  }
}
