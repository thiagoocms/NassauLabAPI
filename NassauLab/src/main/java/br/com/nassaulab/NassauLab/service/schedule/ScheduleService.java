package br.com.nassaulab.NassauLab.service.schedule;

import br.com.nassaulab.NassauLab.dto.schedule.ScheduleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ScheduleService {

    ScheduleDTO create(ScheduleDTO scheduleDTO);

    ScheduleDTO update(ScheduleDTO scheduleDTO, UUID id);

    ScheduleDTO getById(UUID id);

    Page<ScheduleDTO> getAll(Pageable pageable);

    Page<ScheduleDTO> getAllByTeacher(Pageable pageable, UUID registration);

    void delete(UUID id);

}