 package br.com.nassaulab.NassauLab.src.schedule;

import br.com.nassaulab.NassauLab.dto.schedule.ScheduleDTO;
import br.com.nassaulab.NassauLab.dto.teacher.LoginDTO;
import br.com.nassaulab.NassauLab.dto.teacher.TeacherDTO;
import br.com.nassaulab.NassauLab.service.schedule.ScheduleService;
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
public class ScheduleResource {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleResource(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/schedules",
            produces = "application/json"
    )
    public ResponseEntity<Page<ScheduleDTO>> findAll(Pageable pageable){
        Page<ScheduleDTO> page = this.scheduleService.getAll(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/schedules",
            produces = "application/json"
    )
    public ResponseEntity<ScheduleDTO> create(@RequestBody ScheduleDTO dto){
        dto = this.scheduleService.create(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/schedules/{id}",
            produces = "application/json"
    )
    public ResponseEntity<ScheduleDTO> update(@RequestBody ScheduleDTO dto, @PathVariable("id") UUID id){
        dto = this.scheduleService.update(dto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/schedules/{id}",
            produces = "application/json"
    )
    public ResponseEntity<ScheduleDTO> getById(@PathVariable("id") UUID id){
        ScheduleDTO dto = this.scheduleService.getById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/schedules/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        this.scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
