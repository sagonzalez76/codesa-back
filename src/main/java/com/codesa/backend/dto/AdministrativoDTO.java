package com.codesa.backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministrativoDTO extends PersonaDTO {

    @NotBlank
    private String cargo;

    @NotBlank
    private String departamento;
}
