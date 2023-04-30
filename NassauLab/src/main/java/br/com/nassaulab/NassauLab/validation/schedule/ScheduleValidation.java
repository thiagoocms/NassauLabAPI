package br.com.nassaulab.NassauLab.validation.schedule;

import br.com.nassaulab.NassauLab.domain.course.Course;
import br.com.nassaulab.NassauLab.domain.repository.course.CourseRepository;
import br.com.nassaulab.NassauLab.domain.repository.schedule.ScheduleRepository;
import br.com.nassaulab.NassauLab.domain.repository.teacher.TeacherRepository;
import br.com.nassaulab.NassauLab.domain.schedule.Schedule;
import br.com.nassaulab.NassauLab.domain.teacher.Teacher;
import br.com.nassaulab.NassauLab.dto.schedule.ScheduleDTO;
import br.com.nassaulab.NassauLab.dto.teacher.TeacherDTO;
import br.com.nassaulab.NassauLab.exception.BadRequestException;
import br.com.nassaulab.NassauLab.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleValidation {

    private final TeacherRepository teacherRepository;

    private final CourseRepository courseRepository;

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleValidation(TeacherRepository teacherRepository, CourseRepository courseRepository, ScheduleRepository scheduleRepository) {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public void validFields(ScheduleDTO dto){
        List<String> fields = new ArrayList<>();

        if(Objects.isNull(dto.getDate())) {
            fields.add("data");
        }
        if(Objects.isNull(dto.getCourse().getId())) {
            fields.add("turma");
        }
        if(Objects.isNull(dto.getTeacher().getRegistration())) {
            fields.add("professor");
        }

        if (!fields.isEmpty()){
            String message = "Os campos {0} são obrigatorios.";
            message = message.replace("{0}", String.join(", ",fields));
            throw new BadRequestException(message);
        }
    }

    public Schedule validShedule(UUID id){

        Optional<Schedule> scheduleOptional = this.scheduleRepository.findById(id);
        if (!scheduleOptional.isPresent()){
            throw new NotFoundException("agenda não encontrada.");
        }

        return scheduleOptional.get();
    }

    public Schedule validUpdate(ScheduleDTO dto, UUID id){

        Schedule schedule = this.validShedule(id);

        if(!Objects.isNull(dto.getDate())){
            schedule.setDate(dto.getDate());
        }
        if(!Objects.isNull(dto.getTeacher().getRegistration())){
            schedule.getTeacher().setRegistration(dto.getTeacher().getRegistration());
        }
        if(!Objects.isNull(dto.getCourse().getId())){
            schedule.getCourse().setId(dto.getCourse().getId());
        }
        if(!Objects.isNull(dto.getShift())){
            schedule.setShift(dto.getShift());
        }
        return schedule;
    }

    public void validExist(Date date, String shift){
        Optional<Schedule> ScheduleOptional = this.scheduleRepository.findFirstByDateAndShift(date, shift);
        if (ScheduleOptional.isPresent()){
            throw new BadRequestException("ja existe um agendamento com esse turno nessa data");
        }
    }
    public Schedule checkRelacions(Schedule schedule){

       this.checkCourse(schedule);
       this.checkTeacher(schedule);

        return schedule;
    }

    private Schedule checkCourse(Schedule schedule){
    Optional<Course> courseOptional = this.courseRepository.findById(schedule.getCourse().getId());
    if (!courseOptional.isPresent()){
        throw new NotFoundException("turma não encontrada.");
    }

    schedule.setCourse(courseOptional.get());

    return schedule;
    }

    private Schedule checkTeacher(Schedule schedule){

        Optional<Teacher> teacherOptional = this.teacherRepository.findById(schedule.getTeacher().getRegistration());
        if (!teacherOptional.isPresent()){
            throw new NotFoundException("professor não encotrado.");
        }
        schedule.setTeacher(teacherOptional.get());
        return schedule;
    }
}
