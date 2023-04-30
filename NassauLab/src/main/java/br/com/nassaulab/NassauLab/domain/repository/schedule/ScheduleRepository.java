package br.com.nassaulab.NassauLab.domain.repository.schedule;

import br.com.nassaulab.NassauLab.domain.schedule.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    Page<Schedule> findAll(Pageable pageable);

    Page<Schedule> findAllByTeacherRegistration(Pageable pageable, UUID registration);

    Optional<Schedule> findFirstByDateAndShift(Date date, String shift);
}
