package com.codesa.backend.service;

import com.codesa.backend.dto.AdministrativoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdministrativoService {
    AdministrativoDTO crear(AdministrativoDTO dto);
    AdministrativoDTO obtenerPorId(Long id);
    AdministrativoDTO actualizar(Long id, AdministrativoDTO dto);
    void eliminar(Long id);
    Page<AdministrativoDTO> listar(Pageable pageable);
}