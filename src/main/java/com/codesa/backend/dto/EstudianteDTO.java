package com.codesa.backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteDTO extends PersonaDTO {

    @NotBlank(message = "El número de matrícula es obligatorio.")
    @Size(max = 20, message = "El número de matrícula no debe superar los 20 caracteres.")
    private String numeroMatricula;

    @NotBlank(message = "El grado es obligatorio.")
    private String grado;
}
