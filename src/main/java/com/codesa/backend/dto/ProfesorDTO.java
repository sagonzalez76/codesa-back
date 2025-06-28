package com.codesa.backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDTO extends PersonaDTO {

    @NotBlank
    private String especialidad;

    @PastOrPresent
    private LocalDate fechaContratacion;
}
