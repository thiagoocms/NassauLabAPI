package br.com.nassaulab.NassauLab.domain.repository.teacher;

import br.com.nassaulab.NassauLab.domain.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    Page<Teacher> findAll(Pageable pageable);

    Optional<Teacher> findFirstByEmail(String email);

   Optional<Teacher> findFirstByEmailAndPassword(String email, String password);
}
