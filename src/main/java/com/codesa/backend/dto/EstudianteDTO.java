package com.codesa.backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteDTO extends PersonaDTO {

    @NotBlank
    private String numeroMatricula;

    @NotBlank
    private String grado;
}
