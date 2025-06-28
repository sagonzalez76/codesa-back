package com.codesa.backend.service.impl;

import com.codesa.backend.dto.PersonaDTO;
import com.codesa.backend.exception.ResourceNotFoundException;
import com.codesa.backend.model.Persona;
import com.codesa.backend.repository.PersonaRepository;
import com.codesa.backend.service.PersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.codesa.backend.specification.PersonaSpecification;
import org.springframework.data.jpa.domain.Specification;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final ModelMapper modelMapper;

    public PersonaServiceImpl(PersonaRepository personaRepository, ModelMapper modelMapper) {
        this.personaRepository = personaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PersonaDTO crear(PersonaDTO dto) {
        Persona persona = modelMapper.map(dto, Persona.class);
        return modelMapper.map(personaRepository.save(persona), PersonaDTO.class);
    }

    @Override
    public PersonaDTO obtenerPorId(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con id: " + id));
        return modelMapper.map(persona, PersonaDTO.class);
    }

    @Override
    public Page<PersonaDTO> listar(Pageable pageable) {
        return personaRepository.findAll(pageable)
                .map(persona -> modelMapper.map(persona, PersonaDTO.class));
    }

    @Override
    public PersonaDTO actualizar(Long id, PersonaDTO dto) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con id: " + id));
        persona.setNombre(dto.getNombre());
        persona.setApellido(dto.getApellido());
        persona.setEmail(dto.getEmail());
        persona.setFechaNacimiento(dto.getFechaNacimiento());
        persona.setTelefono(dto.getTelefono());
        return modelMapper.map(personaRepository.save(persona), PersonaDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Page<PersonaDTO> buscarPorCriterios(String nombre, String apellido, String email, Pageable pageable) {
        Specification<Persona> spec = Specification.not(null);

        if (nombre != null && !nombre.isBlank()) {
            spec = spec.and(PersonaSpecification.nombreContiene(nombre));
        }

        if (apellido != null && !apellido.isBlank()) {
            spec = spec.and(PersonaSpecification.apellidoContiene(apellido));
        }

        if (email != null && !email.isBlank()) {
            spec = spec.and(PersonaSpecification.emailContiene(email));
        }

        return personaRepository.findAll(spec, pageable)
                .map(persona -> modelMapper.map(persona, PersonaDTO.class));
    }
}