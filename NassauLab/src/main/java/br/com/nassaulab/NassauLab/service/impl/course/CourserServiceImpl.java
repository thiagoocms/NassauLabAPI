package br.com.nassaulab.NassauLab.service.impl.course;

import br.com.nassaulab.NassauLab.domain.course.Course;
import br.com.nassaulab.NassauLab.domain.repository.course.CourseRepository;
import br.com.nassaulab.NassauLab.domain.repository.teacher.TeacherRepository;
import br.com.nassaulab.NassauLab.domain.teacher.Teacher;
import br.com.nassaulab.NassauLab.dto.course.CourseDTO;
import br.com.nassaulab.NassauLab.dto.teacher.LoginDTO;
import br.com.nassaulab.NassauLab.dto.teacher.TeacherDTO;
import br.com.nassaulab.NassauLab.exception.BadRequestException;
import br.com.nassaulab.NassauLab.exception.NotFoundException;
import br.com.nassaulab.NassauLab.service.course.CourseService;
import br.com.nassaulab.NassauLab.service.teacher.TeacherService;
import br.com.nassaulab.NassauLab.validation.teacher.TeacherValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CourserServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourserServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDTO create(CourseDTO courseDTO) {

        Course course = new Course();

        BeanUtils.copyProperties(courseDTO, course);

        course.setId(null);

        course = this.courseRepository.save(course);

        BeanUtils.copyProperties(course, courseDTO);

        return courseDTO;
    }

    @Override
    public CourseDTO getById(UUID id) {

        Optional<Course> courseOptional = this.courseRepository.findById(id);
        if (!courseOptional.isPresent()){
            throw new NotFoundException("turma não encotrada");
        }
        CourseDTO courseDTO = new CourseDTO();

        BeanUtils.copyProperties(courseOptional.get(), courseDTO);

        return courseDTO;
    }

    @Override
    public void delete(UUID id) {

        Optional<Course> courseOptional = this.courseRepository.findById(id);
        if (!courseOptional.isPresent()){
            throw new NotFoundException("turma não encotrada");
        }
        this.courseRepository.delete(courseOptional.get());
    }
}