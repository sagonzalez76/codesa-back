package com.codesa.backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {

    private Long id;

    @NotBlank
    private String nombre;

    private String descripcion;

    @Min(1)
    private int creditos;

    private Long idProfesor;

    public Long getProfesorId() {
        return idProfesor;
    }

    public void setProfesorId(Long idProfesor) {
        this.idProfesor = idProfesor;
    }
}
