package br.com.nassaulab.NassauLab.service.teacher;

import br.com.nassaulab.NassauLab.dto.teacher.LoginDTO;
import br.com.nassaulab.NassauLab.dto.teacher.TeacherDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TeacherService {

    TeacherDTO create(TeacherDTO teacherDTO);

    TeacherDTO update(TeacherDTO teacherDTO, UUID registration);

    TeacherDTO getByRegistration(UUID registration);

    Page<TeacherDTO> getAll(Pageable pageable);

    void delete(UUID registration);

    TeacherDTO login(LoginDTO loginDTO);
}