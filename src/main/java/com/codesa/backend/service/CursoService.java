package com.codesa.backend.service;

import com.codesa.backend.dto.CursoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CursoService {
    CursoDTO crear(CursoDTO dto);
    CursoDTO obtenerPorId(Long id);
    CursoDTO actualizar(Long id, CursoDTO dto);
    void eliminar(Long id);
    Page<CursoDTO> listar(Pageable pageable);
}
