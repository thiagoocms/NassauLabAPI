package br.com.nassaulab.NassauLab.service.impl.schedule;

import br.com.nassaulab.NassauLab.domain.repository.schedule.ScheduleRepository;
import br.com.nassaulab.NassauLab.domain.schedule.Schedule;
import br.com.nassaulab.NassauLab.domain.teacher.Teacher;
import br.com.nassaulab.NassauLab.dto.schedule.ScheduleDTO;
import br.com.nassaulab.NassauLab.service.schedule.ScheduleService;
import br.com.nassaulab.NassauLab.validation.schedule.ScheduleValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleValidation scheduleValidation;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleValidation scheduleValidation) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleValidation = scheduleValidation;
    }

    @Override
    public ScheduleDTO create(ScheduleDTO scheduleDTO) {

        this.scheduleValidation.validFields(scheduleDTO);

        this.scheduleValidation.validExist(scheduleDTO.getDate(), scheduleDTO.getShift());

        Schedule schedule = new Schedule();

        BeanUtils.copyProperties(scheduleDTO, schedule);

        this.scheduleValidation.checkRelacions(schedule);

        schedule.setId(null);

        schedule = this.scheduleRepository.save(schedule);

        BeanUtils.copyProperties(schedule, scheduleDTO);

        return scheduleDTO;
    }

    @Override
    public ScheduleDTO update(ScheduleDTO scheduleDTO, UUID id) {

        Schedule schedule = this.scheduleValidation.validUpdate(scheduleDTO, id);

        this.scheduleValidation.checkRelacions(schedule);

        schedule = this.scheduleRepository.save(schedule);

        BeanUtils.copyProperties(schedule, scheduleDTO);

        return scheduleDTO;
    }

    @Override
    public ScheduleDTO getById(UUID id) {

        Schedule schedule = this.scheduleValidation.validShedule(id);

        ScheduleDTO scheduleDTO = new ScheduleDTO();

        BeanUtils.copyProperties(schedule, scheduleDTO);

        return scheduleDTO;
    }

    @Override
    public Page<ScheduleDTO> getAll(Pageable pageable) {

        Page<Schedule> schedulePage = this.scheduleRepository.findAll(pageable);

        return schedulePage.map(schedule -> {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(schedule, scheduleDTO);
            return scheduleDTO;
        });
    }

    @Override
    public Page<ScheduleDTO> getAllByTeacher(Pageable pageable, UUID registration) {

        Page<Schedule> schedulePage = this.scheduleRepository.findAllByTeacherRegistration(pageable, registration);

        return schedulePage.map(schedule -> {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            BeanUtils.copyProperties(schedule, scheduleDTO);
            return scheduleDTO;
        });
    }

    @Override
    public void delete(UUID id) {
        Schedule schedule = this.scheduleValidation.validShedule(id);
        this.scheduleRepository.delete(schedule);
    }
}
