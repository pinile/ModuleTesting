package org.example;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
@EqualsAndHashCode
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

    @SneakyThrows
    public void addGrade(int grade) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:5352/checkGrade?grade=" + grade);
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        if (!Boolean.parseBoolean(EntityUtils.toString(entity))) {
            throw new IllegalArgumentException(grade + " is wrong grade");
        }
        grades.add(grade);
    }

    @SneakyThrows
    public int rating() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://localhost:5352/educ?sum=" + grades.stream().mapToInt(i -> i).sum());
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        return Integer.parseInt(EntityUtils.toString(entity));
    }
}