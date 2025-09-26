package org.example;

public class StudentRepositoryMock implements StudentRepo {

  public int getRatingForGradeSum(int sum) {
    return 10;
  }

  public long count() {
    return 0;
  }

  public void delete(Student student) {
  }

  public void deleteAll(Iterable<Student> students) {
  }

  public Iterable<Student> getAll() {
    return null;
  }

  public Student save(Student student) {
    return null;
  }

  public Iterable<Student> saveAll(Iterable<Student> students) {
    return null;
  }
}
