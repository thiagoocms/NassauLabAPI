package br.com.nassaulab.NassauLab.dto.teacher;

import br.com.nassaulab.NassauLab.domain.course.Course;
import java.util.List;
import java.util.UUID;

public class TeacherDTO {

    private UUID registration;

    private String name;

    private String email;

    private String password;

    private List<Course> courses;

    public TeacherDTO() {
    }

    public TeacherDTO(UUID registration, String name, String email, String password, List<Course> courses) {
        this.registration = registration;
        this.name = name;
        this.email = email;
        this.password = password;
        this.courses = courses;
    }

    public UUID getRegistration() {
        return registration;
    }

    public void setRegistration(UUID registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}