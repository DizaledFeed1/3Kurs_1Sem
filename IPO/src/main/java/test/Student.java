package test;

import lombok.Data;

@Data
public class Student {
    private String name;
    private Course[] course;
    private int age;

    public Student(String name, int age, Course[] course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }
}
