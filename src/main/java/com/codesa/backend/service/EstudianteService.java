package com.codesa.backend.service;

import com.codesa.backend.dto.EstudianteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstudianteService {

    EstudianteDTO crear(EstudianteDTO dto);

    EstudianteDTO obtenerPorId(Long id);

    EstudianteDTO actualizar(Long id, EstudianteDTO dto);

    void eliminar(Long id);

    Page<EstudianteDTO> listar(Pageable pageable);
}
