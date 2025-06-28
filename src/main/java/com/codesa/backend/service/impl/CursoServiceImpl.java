package com.codesa.backend.service.impl;

import com.codesa.backend.dto.CursoDTO;
import com.codesa.backend.exception.ResourceNotFoundException;
import com.codesa.backend.model.Curso;
import com.codesa.backend.model.Profesor;
import com.codesa.backend.repository.CursoRepository;
import com.codesa.backend.service.CursoService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repository;
    private final ModelMapper mapper;

    public CursoServiceImpl(CursoRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CursoDTO crear(CursoDTO dto) {
        return mapper.map(repository.save(mapper.map(dto, Curso.class)), CursoDTO.class);
    }

    @Override
    public CursoDTO obtenerPorId(Long id) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + id));
        return mapper.map(curso, CursoDTO.class);
    }

    @Override
    public CursoDTO actualizar(Long id, CursoDTO dto) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con id: " + id));

        curso.setNombre(dto.getNombre());
        curso.setDescripcion(dto.getDescripcion());
        curso.setCreditos(dto.getCreditos());

        Profesor profesor = new Profesor();
        profesor.setId(dto.getProfesorId());
        curso.setProfesor(profesor);

        return mapper.map(repository.save(curso), CursoDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<CursoDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(curso -> mapper.map(curso, CursoDTO.class));
    }
}