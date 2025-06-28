package com.codesa.backend.service.impl;

import com.codesa.backend.dto.ProfesorDTO;
import com.codesa.backend.exception.ResourceNotFoundException;
import com.codesa.backend.model.Profesor;
import com.codesa.backend.repository.ProfesorRepository;
import com.codesa.backend.service.ProfesorService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository repository;
    private final ModelMapper mapper;

    public ProfesorServiceImpl(ProfesorRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProfesorDTO crear(ProfesorDTO dto) {
        return mapper.map(repository.save(mapper.map(dto, Profesor.class)), ProfesorDTO.class);
    }

    @Override
    public ProfesorDTO obtenerPorId(Long id) {
        Profesor profesor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado con id: " + id));
        return mapper.map(profesor, ProfesorDTO.class);
    }

    @Override
    public ProfesorDTO actualizar(Long id, ProfesorDTO dto) {
        Profesor profesor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado con id: " + id));

        profesor.setNombre(dto.getNombre());
        profesor.setApellido(dto.getApellido());
        profesor.setFechaNacimiento(dto.getFechaNacimiento());
        profesor.setEmail(dto.getEmail());
        profesor.setTelefono(dto.getTelefono());
        profesor.setEspecialidad(dto.getEspecialidad());
        profesor.setFechaContratacion(dto.getFechaContratacion());

        return mapper.map(repository.save(profesor), ProfesorDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<ProfesorDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(prof -> mapper.map(prof, ProfesorDTO.class));
    }
}