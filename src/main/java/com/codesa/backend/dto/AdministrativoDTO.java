package com.codesa.backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministrativoDTO extends PersonaDTO {

    @NotBlank(message = "El cargo es obligatorio.")
    private String cargo;

    @NotBlank(message = "El departamento es obligatorio.")
    private String departamento;
}
