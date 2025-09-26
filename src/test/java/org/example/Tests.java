package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;

@ExtendWith(EducationExtention.class)
public class Tests {

    @DisplayName("Валидные оценки добавляются в список оценок")
    @RepeatedTest(value = 4, name = "Валидные оценки")
    public void marksInRange(RepetitionInfo repetitionInfo) {
        Student student = new Student("vasya");
        int num = repetitionInfo.getCurrentRepetition() + 1;
        student.setMark(num);
//        Assertions.assertEquals(student.getMarks().getFirst(), num);
        Assertions.assertEquals(List.of(num), student.getMarks());
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

    @Test
    @DisplayName("Тест имени для студента")
    public void testNames() {
        Student student = new Student("123");
        Assertions.assertEquals("123", student.getName());
    }

    @DisplayName("Тест метода equals")
    @RepeatedTest(value = 4, name = "equals для студента")
    public void testEquals(RepetitionInfo repetitionInfo) {
        int num = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList = StudentsGenerator.generateStudents();
        Student student = studentList.get(num);
        Assertions.assertTrue(studentList.get(num).equals(student));
    }

    @Test
    @DisplayName("Негативный тест метода equals")
    public void testEqualsNull() {
        Student student = new Student("123");
        Assertions.assertNotEquals(null, student);
    }

    @Test
    @DisplayName("Негативный тест метода equals")
    public void testEqualsDiffClasses() {
        Student student = new Student("123");
        StudentSubclass studentSubclass = new StudentSubclass("123");
        Assertions.assertNotEquals(student, studentSubclass);
    }

    @Test
    @DisplayName("Валидный тест метода equals, разные объекты")
    public void testEqualsForSimilarNames() {
        Student student1 = new Student("444");
        Student student2 = new Student("444");
        Assertions.assertEquals(student1, student2);
    }

    @Test
    @DisplayName("Валидный тест метода equals, разные объекты")
    public void testEqualsForDifferentNames() {
        Student student1 = new Student("444");
        Student student2 = new Student("555");
        Assertions.assertNotEquals(student1, student2);
    }

    @Test
    @DisplayName("Тест метода hash")
    public void testHashCode() {
        Student student1 = new Student("123");
        student1.setMark(3);
        Student student2 = new Student("123");
        student2.setMark(3);
        Assertions.assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    @DisplayName("Тест изменения имени для студента")
    public void testSetName() {
        Student student = new Student("123");
        student.setName("321");
        Assertions.assertEquals("321", student.getName());
    }
}

class EducationExtention implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("test extention beforeEach");
    }
}