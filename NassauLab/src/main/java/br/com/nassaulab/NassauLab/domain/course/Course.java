package br.com.nassaulab.NassauLab.domain.course;

import br.com.nassaulab.NassauLab.domain.teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_course")
public class Course {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(name = "name")
    private String name;

    @Column(name = "period")
    private String period;

    @Column(name = "shift")
    private String shift;

    @JsonIgnore
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
