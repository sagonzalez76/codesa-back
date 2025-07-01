package com.codesa.backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDTO extends PersonaDTO {

    @NotBlank(message = "La especialidad es obligatoria.")
    private String especialidad;

    @PastOrPresent(message = "La fecha de contratación no puede ser futura.")
    private LocalDate fechaContratacion;
}
