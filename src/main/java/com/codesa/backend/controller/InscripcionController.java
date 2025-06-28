package com.codesa.backend.controller;

import com.codesa.backend.dto.InscripcionDTO;
import com.codesa.backend.service.InscripcionService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "*")
@Tag(name = "Inscripciones", description = "Operaciones CRUD sobre inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @Operation(summary = "Lista todas las inscripciones paginadas")
    @GetMapping
    public ResponseEntity<Page<InscripcionDTO>> listar(
            @PageableDefault(size = 10)
            @Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(inscripcionService.listar(pageable));
    }

    @Operation(summary = "Crea una nueva inscripción")
    @PostMapping
    public ResponseEntity<InscripcionDTO> crear(
            @Valid @RequestBody InscripcionDTO dto) {
        return new ResponseEntity<>(inscripcionService.crear(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene una inscripción por ID")
    @GetMapping("/{id}")
    public ResponseEntity<InscripcionDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.obtenerPorId(id));
    }

    @Operation(summary = "Actualiza una inscripción por ID")
    @PutMapping("/{id}")
    public ResponseEntity<InscripcionDTO> actualizar(@PathVariable Long id,
                                                     @Valid @RequestBody InscripcionDTO dto) {
        return ResponseEntity.ok(inscripcionService.actualizar(id, dto));
    }

    @Operation(summary = "Elimina una inscripción por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
