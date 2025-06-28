package com.codesa.backend.service;

import com.codesa.backend.dto.ProfesorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfesorService {
    ProfesorDTO crear(ProfesorDTO dto);
    ProfesorDTO obtenerPorId(Long id);
    ProfesorDTO actualizar(Long id, ProfesorDTO dto);
    void eliminar(Long id);
    Page<ProfesorDTO> listar(Pageable pageable);
}