package com.codesa.backend.repository;

import com.codesa.backend.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar cursos por nombre o categoría
    // List<Curso> findByNombreContaining(String nombre);
}
