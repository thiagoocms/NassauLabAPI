package br.com.nassaulab.NassauLab.domain.schedule;

import br.com.nassaulab.NassauLab.domain.course.Course;
import br.com.nassaulab.NassauLab.domain.teacher.Teacher;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_schedule")
public class Schedule {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;


    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "teacher_registration", referencedColumnName = "registration")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @Column(name = "shift")
    private String shift;

    public Schedule() {
    }

    public Schedule(UUID id, Date date, Teacher teacher, Course course) {
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