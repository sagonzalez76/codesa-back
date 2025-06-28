package com.codesa.backend.service;

import com.codesa.backend.dto.PersonaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonaService {
    PersonaDTO crear(PersonaDTO dto);
    PersonaDTO obtenerPorId(Long id);
    Page<PersonaDTO> listar(Pageable pageable);
    PersonaDTO actualizar(Long id, PersonaDTO dto);
    void eliminar(Long id);
    Page<PersonaDTO> buscarPorCriterios(String nombre, String apellido, String email, Pageable pageable);
}
