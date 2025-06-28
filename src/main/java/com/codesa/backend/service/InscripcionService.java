package com.codesa.backend.service;

import com.codesa.backend.dto.InscripcionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InscripcionService {
    InscripcionDTO crear(InscripcionDTO dto);
    InscripcionDTO obtenerPorId(Long id);
    InscripcionDTO actualizar(Long id, InscripcionDTO dto);
    void eliminar(Long id);
    Page<InscripcionDTO> listar(Pageable pageable);
}
