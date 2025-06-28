package com.codesa.backend.dto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {

    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @Past
    private LocalDate fechaNacimiento;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "\\d+", message = "El teléfono debe contener solo números")
    private String telefono;
}