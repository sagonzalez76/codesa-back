package com.codesa.backend.service.impl;

import com.codesa.backend.dto.InscripcionDTO;
import com.codesa.backend.exception.ResourceNotFoundException;
import com.codesa.backend.model.Curso;
import com.codesa.backend.model.Estudiante;
import com.codesa.backend.model.Inscripcion;
import com.codesa.backend.repository.InscripcionRepository;
import com.codesa.backend.service.InscripcionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.codesa.backend.repository.CursoRepository;
import com.codesa.backend.repository.EstudianteRepository;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository repository;
    private final ModelMapper mapper;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;



    public InscripcionServiceImpl(InscripcionRepository repository, ModelMapper mapper, EstudianteRepository estudianteRepository, CursoRepository cursoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;

    }

    @Override
    public InscripcionDTO crear(InscripcionDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getIdEstudiante())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + dto.getIdEstudiante()));

        Curso curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + dto.getIdCurso()));

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);
        inscripcion.setFechaInscripcion(dto.getFechaInscripcion());

        return mapper.map(repository.save(inscripcion), InscripcionDTO.class);
    }

    @Override
    public InscripcionDTO obtenerPorId(Long id) {
        Inscripcion insc = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con id: " + id));
        return mapper.map(insc, InscripcionDTO.class);
    }

    @Override
    public InscripcionDTO actualizar(Long id, InscripcionDTO dto) {
        Inscripcion inscripcion = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada con id: " + id));

        Estudiante estudiante = estudianteRepository.findById(dto.getIdEstudiante())
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con ID: " + dto.getIdEstudiante()));

        Curso curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado con ID: " + dto.getIdCurso()));

        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);
        inscripcion.setFechaInscripcion(dto.getFechaInscripcion());

        return mapper.map(repository.save(inscripcion), InscripcionDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<InscripcionDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(insc -> mapper.map(insc, InscripcionDTO.class));
    }
}
