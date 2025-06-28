package com.codesa.backend.controller;

import com.codesa.backend.dto.EstudianteDTO;
import com.codesa.backend.service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*")
@Tag(name = "Estudiantes", description = "CRUD de estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @Operation(summary = "Lista estudiantes paginados")
    @GetMapping
    public ResponseEntity<Page<EstudianteDTO>> listar(
            @PageableDefault(size = 10, sort = "nombre", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(estudianteService.listar(pageable));
    }

    @Operation(summary = "Obtiene estudiante por ID")
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(estudianteService.obtenerPorId(id));
    }

    @Operation(summary = "Crear nuevo estudiante")
    @PostMapping
    public ResponseEntity<EstudianteDTO> crear(@Valid @RequestBody EstudianteDTO dto) {
        EstudianteDTO creado = estudianteService.crear(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza estudiante por ID")
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EstudianteDTO dto) {
        return ResponseEntity.ok(estudianteService.actualizar(id, dto));
    }

    @Operation(summary = "Elimina estudiante por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
