package com.codesa.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio.")
    private String apellido;

    @Past(message = "La fecha de nacimiento debe ser en el pasado.")
    private LocalDate fechaNacimiento;

    @Email(message = "El correo electrónico debe ser válido.")
    @NotBlank(message = "El correo electrónico es obligatorio.")
    private String email;

    @Pattern(regexp = "\\d+", message = "El teléfono debe contener solo números.")
    @Size(min = 10, max = 10, message = "El teléfono debe tener exactamente 10 dígitos.")
    private String telefono;
}
