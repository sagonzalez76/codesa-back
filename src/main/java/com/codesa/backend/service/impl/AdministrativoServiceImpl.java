package com.codesa.backend.service.impl;

import com.codesa.backend.dto.AdministrativoDTO;
import com.codesa.backend.exception.ResourceNotFoundException;
import com.codesa.backend.model.Administrativo;
import com.codesa.backend.repository.AdministrativoRepository;
import com.codesa.backend.service.AdministrativoService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdministrativoServiceImpl implements AdministrativoService {

    private final AdministrativoRepository repository;
    private final ModelMapper mapper;

    public AdministrativoServiceImpl(AdministrativoRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AdministrativoDTO crear(AdministrativoDTO dto) {
        return mapper.map(repository.save(mapper.map(dto, Administrativo.class)), AdministrativoDTO.class);
    }

    @Override
    public AdministrativoDTO obtenerPorId(Long id) {
        Administrativo admin = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrativo no encontrado con id: " + id));
        return mapper.map(admin, AdministrativoDTO.class);
    }

    @Override
    public AdministrativoDTO actualizar(Long id, AdministrativoDTO dto) {
        Administrativo admin = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrativo no encontrado con id: " + id));

        admin.setNombre(dto.getNombre());
        admin.setApellido(dto.getApellido());
        admin.setFechaNacimiento(dto.getFechaNacimiento());
        admin.setEmail(dto.getEmail());
        admin.setTelefono(dto.getTelefono());
        admin.setCargo(dto.getCargo());
        admin.setDepartamento(dto.getDepartamento());

        return mapper.map(repository.save(admin), AdministrativoDTO.class);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<AdministrativoDTO> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(admin -> mapper.map(admin, AdministrativoDTO.class));
    }
}