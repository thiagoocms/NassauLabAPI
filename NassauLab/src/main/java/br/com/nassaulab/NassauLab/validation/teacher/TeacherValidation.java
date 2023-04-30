package br.com.nassaulab.NassauLab.validation.teacher;

import br.com.nassaulab.NassauLab.domain.course.Course;
import br.com.nassaulab.NassauLab.domain.repository.course.CourseRepository;
import br.com.nassaulab.NassauLab.domain.repository.teacher.TeacherRepository;
import br.com.nassaulab.NassauLab.domain.teacher.Teacher;
import br.com.nassaulab.NassauLab.dto.teacher.TeacherDTO;
import br.com.nassaulab.NassauLab.exception.BadRequestException;
import br.com.nassaulab.NassauLab.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeacherValidation {

    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public TeacherValidation(TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public void validFields(TeacherDTO dto){
        List<String> fields = new ArrayList<>();

        if(Objects.isNull(dto.getName())){
        fields.add("nome");
        }
        if(Objects.isNull(dto.getEmail())){
            fields.add("E-mail");
        }
        if(Objects.isNull(dto.getPassword())){
            fields.add("senha");
        }

        if (!fields.isEmpty()){
            String message = "Os campos {0} são obrigatorios.";
            message = message.replace("{0}", String.join(", ",fields));
            throw new BadRequestException(message);
        }
    }

    public Teacher validTeacher(UUID registration){

        Optional<Teacher> teacherOptional = this.teacherRepository.findById(registration);
        if (!teacherOptional.isPresent()){
            throw new NotFoundException("Professor não encontrado.");
        }

        return teacherOptional.get();
    }

    public Teacher validUpdate(TeacherDTO teacherDTO, UUID registration){

        Teacher teacher = this.validTeacher(registration);

        if(!Objects.isNull(teacherDTO.getEmail())){
            teacher.setEmail(teacherDTO.getEmail());
        }
        if(!Objects.isNull(teacherDTO.getName())){
            teacher.setName(teacherDTO.getName());
        }
        if(!Objects.isNull(teacherDTO.getPassword())){
            teacher.setPassword(teacherDTO.getPassword());
        }

        return teacher;
    }
    public void validExist(String email){
        Optional<Teacher> teacherOptional = this.teacherRepository.findFirstByEmail(email);
        if (teacherOptional.isPresent()){
            throw new BadRequestException("ja existe um registro com esse email");
        }
    }
    public Teacher checkRelacions(Teacher teacher){

        List<Course> courses = this.courseRepository.findAllByTeacherRegistration(teacher.getRegistration());

        teacher.setCourses(courses);

        return teacher;
    }
}
