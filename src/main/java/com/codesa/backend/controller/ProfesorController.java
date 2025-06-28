package com.codesa.backend.controller;

import com.codesa.backend.dto.ProfesorDTO;
import com.codesa.backend.service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profesores")
@CrossOrigin(origins = "*")
@Tag(name = "Profesores", description = "Operaciones CRUD sobre profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @Operation(summary = "Lista todos los profesores paginados")
    @ApiResponse(responseCode = "200", description = "Lista de profesores",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProfesorDTO.class)))
    @GetMapping
    public ResponseEntity<Page<ProfesorDTO>> listar(
            @PageableDefault(size = 10)
            @Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(profesorService.listar(pageable));
    }

    @Operation(summary = "Crea un nuevo profesor")
    @PostMapping
    public ResponseEntity<ProfesorDTO> crear(@Valid @RequestBody ProfesorDTO dto) {
        return new ResponseEntity<>(profesorService.crear(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene un profesor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(profesorService.obtenerPorId(id));
    }

    @Operation(summary = "Actualiza un profesor por ID")
    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProfesorDTO dto) {
        return ResponseEntity.ok(profesorService.actualizar(id, dto));
    }

    @Operation(summary = "Elimina un profesor por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        profesorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
