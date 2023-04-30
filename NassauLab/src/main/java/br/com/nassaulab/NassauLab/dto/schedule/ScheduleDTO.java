package br.com.nassaulab.NassauLab.dto.schedule;

import br.com.nassaulab.NassauLab.domain.course.Course;
import br.com.nassaulab.NassauLab.domain.teacher.Teacher;

import java.util.Date;
import java.util.UUID;

public class ScheduleDTO {

    private UUID id;

    private Date date;

    private Teacher teacher;

    private Course course;

    private String shift;

    public ScheduleDTO() {
    }

    public ScheduleDTO(UUID id, Date date, Teacher teacher, Course course) {
        this.id = id;
        this.date = date;
        this.teacher = teacher;
        this.course = course;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}