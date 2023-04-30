package br.com.nassaulab.NassauLab.service.impl.teacher;

import br.com.nassaulab.NassauLab.domain.repository.teacher.TeacherRepository;
import br.com.nassaulab.NassauLab.domain.teacher.Teacher;
import br.com.nassaulab.NassauLab.dto.teacher.LoginDTO;
import br.com.nassaulab.NassauLab.dto.teacher.TeacherDTO;
import br.com.nassaulab.NassauLab.exception.BadRequestException;
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
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final TeacherValidation teacherValidation;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherValidation teacherValidation) {
        this.teacherRepository = teacherRepository;
        this.teacherValidation = teacherValidation;
    }

    @Override
    public TeacherDTO create(TeacherDTO teacherDTO) {

        this.teacherValidation.validFields(teacherDTO);

        this.teacherValidation.validExist(teacherDTO.getEmail());

        Teacher teacher = new Teacher();

        BeanUtils.copyProperties(teacherDTO, teacher);

        teacher.setRegistration(null);

        teacher = this.teacherRepository.save(teacher);

        this.teacherValidation.checkRelacions(teacher);

        BeanUtils.copyProperties(teacher,teacherDTO);

        return teacherDTO;
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO, UUID registration) {

       Teacher teacher = this.teacherValidation.validUpdate(teacherDTO, registration);

       teacher = this.teacherRepository.save(teacher);

        this.teacherValidation.checkRelacions(teacher);


        TeacherDTO dto = new TeacherDTO();

       BeanUtils.copyProperties(teacher, dto);

        return dto;
    }

    @Override
    public TeacherDTO getByRegistration(UUID registration) {

        Teacher teacher = this.teacherValidation.validTeacher(registration);

        this.teacherValidation.checkRelacions(teacher);

        TeacherDTO teacherDTO = new TeacherDTO();

        BeanUtils.copyProperties(teacher,teacherDTO);

        return teacherDTO;
    }

    @Override
    public Page<TeacherDTO> getAll(Pageable pageable) {
        Page<Teacher> teacherPage = this.teacherRepository.findAll(pageable);

        return teacherPage.map(teacher -> {
          TeacherDTO teacherDTO = new TeacherDTO();
            this.teacherValidation.checkRelacions(teacher);
            BeanUtils.copyProperties(teacher,teacherDTO);
            return teacherDTO;
        });
    }

    @Override
    public void delete(UUID registration) {

        Teacher teacher = this.teacherValidation.validTeacher(registration);

        this.teacherRepository.delete(teacher);
    }

    @Override
    public TeacherDTO login(LoginDTO loginDTO){

        Optional<Teacher> teacherOptional = this.teacherRepository.findFirstByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());

        if (!teacherOptional.isPresent()){
            throw new BadRequestException("Usuario ou senha errados");
        }
        Teacher teacher = this.teacherValidation.checkRelacions(teacherOptional.get());

        TeacherDTO teacherDTO = new TeacherDTO();

        BeanUtils.copyProperties(teacher, teacherDTO);

        return teacherDTO;
    }
}