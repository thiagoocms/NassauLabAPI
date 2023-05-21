package br.com.nassaulab.NassauLab.dto.course;

import br.com.nassaulab.NassauLab.domain.teacher.Teacher;
import br.com.nassaulab.NassauLab.dto.teacher.TeacherDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.UUID;

public class CourseDTO {


    private UUID id;

    private String name;

    private String period;

    private String shift;

    @JsonIgnore
    private Teacher teacher;

    public CourseDTO() {
    }

    public CourseDTO(UUID id, String name, String period, String shift, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.shift = shift;
        this.teacher = teacher;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
