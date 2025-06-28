package com.codesa.backend.service.impl;

import com.codesa.backend.dto.EstudianteDTO;
import com.codesa.backend.exception.ResourceNotFoundException;
import com.codesa.backend.model.Estudiante;
import com.codesa.backend.repository.EstudianteRepository;
import com.codesa.backend.service.EstudianteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository, ModelMapper modelMapper) {
        this.estudianteRepository = estudianteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EstudianteDTO crear(EstudianteDTO dto) {
        if (estudianteRepository.existsByNumeroMatricula(dto.getNumeroMatricula())) {
            throw new IllegalArgumentException("Ya existe un estudiante con esa matrícula.");
        }

        Estudiante estudiante = modelMapper.map(dto, Estudiante.class);
        return modelMapper.map(estudianteRepository.save(estudiante), EstudianteDTO.class);
    }

    @Override
    public EstudianteDTO obtenerPorId(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con id: " + id));
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }

    @Override
    public EstudianteDTO actualizar(Long id, EstudianteDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con id: " + id));

        // Actualizamos todos los campos heredados y propios
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setFechaNacimiento(dto.getFechaNacimiento());
        estudiante.setEmail(dto.getEmail());
        estudiante.setTelefono(dto.getTelefono());
        estudiante.setNumeroMatricula(dto.getNumeroMatricula());
        estudiante.setGrado(dto.getGrado());

        return modelMapper.map(estudianteRepository.save(estudiante), EstudianteDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Estudiante no encontrado con id: " + id);
        }
        estudianteRepository.deleteById(id);
    }

    @Override
    public Page<EstudianteDTO> listar(Pageable pageable) {
        return estudianteRepository.findAll(pageable)
                .map(est -> modelMapper.map(est, EstudianteDTO.class));
    }
}
