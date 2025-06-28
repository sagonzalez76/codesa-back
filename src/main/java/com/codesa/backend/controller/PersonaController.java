package com.codesa.backend.controller;

import com.codesa.backend.dto.PersonaDTO;
import com.codesa.backend.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "*")
@Tag(name = "Personas", description = "Operaciones CRUD sobre personas")

public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Operation(summary = "Listar todas las personas paginadas")
    @ApiResponse(responseCode = "200", description = "Lista de personas",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonaDTO.class)))
    @GetMapping
    public ResponseEntity<Page<PersonaDTO>> listar(
            @PageableDefault(size = 2, sort = "nombre", direction = Sort.Direction.ASC)
            @Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(personaService.listar(pageable));
    }

    @Operation(summary = "Busca personas por criterios")
    @ApiResponse(responseCode = "200", description = "Lista de personas encontradas")
    @GetMapping("/search")
    public ResponseEntity<Page<PersonaDTO>> buscarPorCriterios(
            @Parameter(description = "Nombre de la persona") @RequestParam(required = false) String nombre,
            @Parameter(description = "Apellido de la persona") @RequestParam(required = false) String apellido,
            @Parameter(description = "Correo electrónico de la persona") @RequestParam(required = false) String email,
            @PageableDefault(size = 10, sort = "nombre", direction = Sort.Direction.ASC)
            @Parameter(hidden = true) Pageable pageable) {
        return ResponseEntity.ok(personaService.buscarPorCriterios(nombre, apellido, email, pageable));
    }

    @Operation(summary = "Crea una nueva persona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Persona creada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PersonaDTO> crear(
            @Parameter(description = "Datos de la persona a crear", required = true)
            @Valid @RequestBody PersonaDTO dto) {
        return new ResponseEntity<>(personaService.crear(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene persona por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persona encontrada"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> obtener(
            @Parameter(description = "ID de la persona", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(personaService.obtenerPorId(id));
    }

    @Operation(summary = "Actualiza persona por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persona actualizada"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> actualizar(
            @Parameter(description = "ID de la persona a actualizar", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevos datos de la persona", required = true)
            @Valid @RequestBody PersonaDTO dto) {
        return ResponseEntity.ok(personaService.actualizar(id, dto));
    }

    @Operation(summary = "Elimina persona por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Persona eliminada"),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la persona a eliminar", required = true)
            @PathVariable Long id) {
        personaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}

