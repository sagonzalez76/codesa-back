package com.codesa.backend.controller;

import com.codesa.backend.dto.CursoDTO;
import com.codesa.backend.service.CursoService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
@Tag(name = "Cursos", description = "Operaciones CRUD sobre cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Operation(summary = "Lista todos los cursos paginados")
    @GetMapping
    public ResponseEntity<Page<CursoDTO>> listar(
            @PageableDefault(size = 10)
            @Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(cursoService.listar(pageable));
    }

    @Operation(summary = "Crea un nuevo curso")
    @PostMapping
    public ResponseEntity<CursoDTO> crear(
            @Valid @RequestBody CursoDTO dto) {
        return new ResponseEntity<>(cursoService.crear(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene un curso por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obtenerPorId(id));
    }

    @Operation(summary = "Actualiza un curso por ID")
    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> actualizar(@PathVariable Long id,
                                               @Valid @RequestBody CursoDTO dto) {
        return ResponseEntity.ok(cursoService.actualizar(id, dto));
    }

    @Operation(summary = "Elimina un curso por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
