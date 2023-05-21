package br.com.nassaulab.NassauLab.service.course;

import br.com.nassaulab.NassauLab.dto.course.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CourseService {

    CourseDTO create(CourseDTO courseDTO);

    CourseDTO getById(UUID id);

    List<CourseDTO> getAllByTeacher(UUID teacherRegistration);

    List<CourseDTO> getAll();

    void delete(UUID id);
}