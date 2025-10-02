package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.List;

import static org.example.GradeMock.gradeCheck;


public class Tests {

    @DisplayName("Валидные оценки")
    @ParameterizedTest(name = "[{index}]. Валидная оценка [{0}] добавляется в список оценок")
    @ValueSource(ints = {2, 3, 4, 5})
    public void gradesInRange(final int ints) {
        boolean check = gradeCheck(ints);
        Assertions.assertTrue(check);
    }

    @DisplayName("Невалидные оценки")
    @ParameterizedTest(name = "[{index}]. Невалидная оценка [{0}] не добавляется в список оценок")
    @ValueSource(ints = {0, 1, 6, 75, -6})
    public void gradesNotInRange(final int ints) {
        boolean check = gradeCheck(ints);
        Assertions.assertFalse(check);
    }

    @Test
    @DisplayName("Пустое репо для студента кидает исключение")
    @Disabled
    public void testRatingNullRepo() {
        Student student = new Student("vasya");
        student.addGrade(3);

        Assertions.assertThrows(IllegalStateException.class, () -> student.rating());
    }

    @Test
    @DisplayName("Тест рейтинга для студента")
    @Disabled
    public void testRating() {
        Student student = new Student("vasya");
        StudentRepo studentRepo = Mockito.mock(StudentRepo.class);
        student.setStudentRepo(studentRepo);
        Mockito.when(studentRepo.getRatingForGradeSum(Mockito.anyInt())).thenReturn(10);
        Assertions.assertEquals(10, student.rating());
    }

    @Test
    @DisplayName("Тест рейтинга для студента (Verify)")
    @Disabled
    public void testRatingWithVerify() {
        Student student = new Student("vasya");
        StudentRepo studentRepo = Mockito.mock(StudentRepo.class);
        student.setStudentRepo(studentRepo);

        student.addGrade(3);
        student.addGrade(4);

        int expectedSum = 7;

        Mockito.when(studentRepo.getRatingForGradeSum(expectedSum)).thenReturn(99);

        int rating = student.rating();

        Mockito.verify(studentRepo).getRatingForGradeSum(expectedSum);
        Assertions.assertEquals(99, rating);
    }

    @Test
    @DisplayName("Тест имени для студента")
    public void testNames() {
        Student student = new Student("123");
        Assertions.assertEquals("123", student.getName());
    }

    @DisplayName("Тест метода equals (Рефлексивность)")
    @RepeatedTest(value = 4, name = "equals для студента ")
    public void testEqualsReflexive(RepetitionInfo repetitionInfo) {
        int num = repetitionInfo.getCurrentRepetition() - 1;
        List<Student> studentList = List.of(
                new Student("vasya"),
                new Student("pete"),
                new Student("masha"),
                new Student("pasha")
        );

        Student student = studentList.get(num);
        Assertions.assertTrue(studentList.get(num).equals(student));
    }

    @Test
    @DisplayName("Негативный тест метода equals")
    public void testEqualsNull() {
        Student student = new Student("123");
        Assertions.assertFalse(student.equals(null));
    }

    @Test
    @DisplayName("Негативный тест метода equals")
    @Disabled
    public void testEqualsDiffClasses() {
        Student student = new Student("123");
        StudentSubclass studentSubclass = new StudentSubclass("123");
        Assertions.assertNotEquals(student, studentSubclass);
    }

    @Test
    @DisplayName("Валидный тест метода equals (Симметрия)")
    @Disabled
    public void testEqualsSymmetric() {
        Student student1 = new Student("444");
        student1.addGrade(2);
        Student student2 = new Student("444");
        student2.addGrade(2);
        Assertions.assertEquals(student1.equals(student2), student2.equals(student1));
    }

    @Test
    @DisplayName("Валидный тест метода equals (Согласованность)")
    @Disabled
    public void testEqualsConsistent() {
        Student student1 = new Student("444");
        student1.addGrade(2);
        Student student2 = new Student("444");
        student2.addGrade(2);
        Assertions.assertTrue(student1.equals(student2));
        Assertions.assertTrue(student1.equals(student2));
    }

    @Test
    @DisplayName("Негативный тест метода equals (Согласованность)")
    @Disabled
    public void testEqualsConsistentNegative() {
        Student student1 = new Student("444");
        student1.addGrade(2);
        Student student2 = new Student("444");
        student2.addGrade(2);
        Assertions.assertTrue(student1.equals(student2));
        student2.addGrade(3);
        Assertions.assertFalse(student1.equals(student2));
    }

    @Test
    @DisplayName("Валидный тест метода equals (Транзитивность)")
    @Disabled
    public void testEqualsTransitive() {
        Student student1 = new Student("444");
        student1.addGrade(2);
        Student student2 = new Student("444");
        student2.addGrade(2);
        Student student3 = new Student("444");
        student3.addGrade(2);

        Assertions.assertTrue(student1.equals(student2));
        Assertions.assertTrue(student2.equals(student1));
        Assertions.assertTrue(student1.equals(student3));
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
    @Disabled
    public void testHashCode() {
        Student student1 = new Student("123");
        student1.addGrade(3);
        Student student2 = new Student("123");
        student2.addGrade(3);
        Assertions.assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    @DisplayName("Негативный тест метода hash")
    @Disabled
    public void testHashCodeNegative() {
        Student student1 = new Student("123");
        student1.addGrade(3);
        Student student2 = new Student("123");
        student2.addGrade(4);
        Assertions.assertNotEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    @DisplayName("Тест изменения имени для студента")
    public void testSetName() {
        Student student = new Student("123");
        student.setName("321");
        Assertions.assertEquals("321", student.getName());
    }

    @Test
    @DisplayName("Тест модифицируемость списка оценок для студента")
    public void testSetGrade() {
        Student student = new Student("123");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> student.getGrades().add(4));
    }

}