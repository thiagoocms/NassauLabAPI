package br.com.nassaulab.NassauLab.src.teacher;

import br.com.nassaulab.NassauLab.dto.teacher.LoginDTO;
import br.com.nassaulab.NassauLab.dto.teacher.TeacherDTO;
import br.com.nassaulab.NassauLab.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("v1/api")
@CrossOrigin("*")
public class TeacherResource {

    private final TeacherService teacherService;

    @Autowired
    public TeacherResource(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/teachers",
            produces = "application/json"
    )
    public ResponseEntity<Page<TeacherDTO>> findAll(Pageable pageable){
        Page<TeacherDTO> page = this.teacherService.getAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/teachers",
            produces = "application/json"
    )
    public ResponseEntity<TeacherDTO> create(@RequestBody TeacherDTO teacherDTO){
        teacherDTO = this.teacherService.create(teacherDTO);
        return new ResponseEntity<>(teacherDTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/teachers/{registration}",
            produces = "application/json"
    )
    public ResponseEntity<TeacherDTO> update(@RequestBody TeacherDTO teacherDTO, @PathVariable("registration") UUID registration){
        teacherDTO = this.teacherService.update(teacherDTO, registration);
        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/teachers/{registration}",
            produces = "application/json"
    )
    public ResponseEntity<TeacherDTO> getByRegistration(@PathVariable("registration") UUID registration){
        TeacherDTO teacherDTO = this.teacherService.getByRegistration(registration);
        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/teachers/{registration}",
            produces = "application/json"
    )
    public ResponseEntity<Void> delete(@PathVariable("registration") UUID registration){
        this.teacherService.delete(registration);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/teachers-login",
            produces = "application/json"
    )
    public ResponseEntity<TeacherDTO> login(@RequestBody LoginDTO loginDTO){
        TeacherDTO teacherDTO = this.teacherService.login(loginDTO);
        return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
    }
}
