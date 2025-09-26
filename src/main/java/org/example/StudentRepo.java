package org.example;

public interface StudentRepo {
  int getRatingForGradeSum(int sum);
  long count();
  void delete(Student student);
  void deleteAll(Iterable<Student> students);
  Iterable<Student> getAll();
  Student save(Student student);
  Iterable<Student> saveAll(Iterable<Student> students);
}
