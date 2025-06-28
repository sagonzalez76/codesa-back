package com.codesa.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @Past
    private LocalDate fechaNacimiento;

    @Email
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "\\d+")
    private String telefono;
}
