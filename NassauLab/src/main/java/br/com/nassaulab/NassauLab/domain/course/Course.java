package br.com.nassaulab.NassauLab.domain.course;

import br.com.nassaulab.NassauLab.domain.teacher.Teacher;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "name")
    private String name;

    @Column(name = "period")
    private String period;

    @Column(name = "shift")
    private String shift;

    @ManyToOne
    @JoinColumn(name = "teacher_registration")
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course() {
    }

    public Course(UUID id, String name, String period, String shift) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.shift = shift;
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
}
