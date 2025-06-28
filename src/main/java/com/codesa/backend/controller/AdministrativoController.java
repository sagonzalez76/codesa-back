package com.codesa.backend.controller;

import com.codesa.backend.dto.AdministrativoDTO;
import com.codesa.backend.service.AdministrativoService;
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
@RequestMapping("/api/administrativos")
@CrossOrigin(origins = "*")
@Tag(name = "Administrativos", description = "Operaciones CRUD sobre administrativos")
public class AdministrativoController {

    private final AdministrativoService administrativoService;

    public AdministrativoController(AdministrativoService administrativoService) {
        this.administrativoService = administrativoService;
    }

    @Operation(summary = "Lista todos los administrativos paginados")
    @ApiResponse(responseCode = "200", description = "Lista de administrativos",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AdministrativoDTO.class)))
    @GetMapping
    public ResponseEntity<Page<AdministrativoDTO>> listar(
            @PageableDefault(size = 10)
            @Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(administrativoService.listar(pageable));
    }

    @Operation(summary = "Crea un nuevo administrativo")
    @PostMapping
    public ResponseEntity<AdministrativoDTO> crear(@Valid @RequestBody AdministrativoDTO dto) {
        return new ResponseEntity<>(administrativoService.crear(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene un administrativo por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AdministrativoDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(administrativoService.obtenerPorId(id));
    }

    @Operation(summary = "Actualiza un administrativo por ID")
    @PutMapping("/{id}")
    public ResponseEntity<AdministrativoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody AdministrativoDTO dto) {
        return ResponseEntity.ok(administrativoService.actualizar(id, dto));
    }

    @Operation(summary = "Elimina un administrativo por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        administrativoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
