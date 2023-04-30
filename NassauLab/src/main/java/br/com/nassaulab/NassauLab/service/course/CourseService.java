package br.com.nassaulab.NassauLab.service.course;

import br.com.nassaulab.NassauLab.dto.course.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CourseService {

    CourseDTO create(CourseDTO courseDTO);

    CourseDTO getById(UUID id);

    void delete(UUID id);
}