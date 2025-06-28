package com.codesa.backend.repository;

import com.codesa.backend.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByNumeroMatricula(String numeroMatricula);
    boolean existsByNumeroMatricula(String numeroMatricula);
}
