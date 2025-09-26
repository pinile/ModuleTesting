package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

@ExtendWith(EducationExtention.class)
public class Tests {

  @DisplayName("Валидные оценки добавляются в список оценок")
  @RepeatedTest(value = 4, name = "Валидные оценки")
  public void marksInRange(RepetitionInfo repetitionInfo) {
    Student student = new Student("vasya");
    int num = repetitionInfo.getCurrentRepetition() + 1;
    student.setMark(num);
    Assertions.assertEquals(student.getMarks().get(0), num);
  }

  @DisplayName("Невалидные оценки кидают исключения")
  @ParameterizedTest(name = "Невалидные оценки")
  @MethodSource("org.example.MarksGenerator#ints")
  public void marksNotInRange(int mark) {
    Student student = new Student("vasya_pupkin");
    Assertions.assertThrows(IllegalArgumentException.class, () -> student.setMark(mark));
  }

  @Test
  @DisplayName("Тест рейтинга для студента")
  public void testRating() {
    Student student = new Student("vasya");

    StudentRepo studentRepo = Mockito.mock(StudentRepo.class);
    student.setStudentRepo(studentRepo);
    Mockito.when(studentRepo.getRatingForGradeSum(Mockito.anyInt())).thenReturn(10);

    Assertions.assertEquals(10, student.rating());
  }
}

class EducationExtention implements BeforeEachCallback {

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    System.out.println("test extention beforeEach");
  }
}