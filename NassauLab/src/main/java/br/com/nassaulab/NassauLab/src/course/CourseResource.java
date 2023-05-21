package br.com.nassaulab.NassauLab.src.course;

import br.com.nassaulab.NassauLab.dto.course.CourseDTO;
import br.com.nassaulab.NassauLab.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api")
@CrossOrigin("*")
public class CourseResource {

    private final CourseService courseService;

    @Autowired
    public CourseResource(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/courses",
            produces = "application/json"
    )
    public ResponseEntity<CourseDTO> create(@RequestBody CourseDTO DTO){
        DTO = this.courseService.create(DTO);
        return new ResponseEntity<>(DTO, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/courses/{id}",
            produces = "application/json"
    )
    public ResponseEntity<CourseDTO> getById(@PathVariable("id") UUID id){
        CourseDTO DTO = this.courseService.getById(id);
        return new ResponseEntity<>(DTO, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/courses/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        this.courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/courses/byTeacher/{registration}",
            produces = "application/json"
    )
    public ResponseEntity<List<CourseDTO>> getAllByTeacher(UUID teacherRegistration){
        List<CourseDTO> courseDTOList = this.courseService.getAllByTeacher(teacherRegistration);
        return new ResponseEntity<>(courseDTOList ,HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/courses",
            produces = "application/json"
    )
    public ResponseEntity<List<CourseDTO>> getAll(){
        List<CourseDTO> courseDTOList = this.courseService.getAll();
        return new ResponseEntity<>(courseDTOList ,HttpStatus.OK);
    }
}
