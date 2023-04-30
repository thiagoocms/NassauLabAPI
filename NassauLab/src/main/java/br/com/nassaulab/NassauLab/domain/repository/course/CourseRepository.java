package br.com.nassaulab.NassauLab.domain.repository.course;

import br.com.nassaulab.NassauLab.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    List<Course> findAllByTeacherRegistration(UUID teacherRegistration);
}
