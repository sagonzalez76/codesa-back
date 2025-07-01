package com.codesa.backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {

    private Long id;

    @NotBlank(message = "El nombre del curso es obligatorio.")
    private String nombre;

    private String descripcion;

    @Min(value = 1, message = "Los créditos deben ser al menos 1.")
    private int creditos;

    @NotNull(message = "Debe seleccionarse un profesor.")
    private Long idProfesor;

    public Long getProfesorId() {
        return idProfesor;
    }

    public void setProfesorId(Long idProfesor) {
        this.idProfesor = idProfesor;
    }
}
