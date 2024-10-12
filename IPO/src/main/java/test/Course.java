package test;

import lombok.Data;

@Data
public class Course {
    private String title;
    private Teacher teacher; // Ссылка на ещё один класс

    public Course(String title, Teacher teacher) {
        this.title = title;
        this.teacher = teacher;
    }
}