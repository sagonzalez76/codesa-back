package com.codesa.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id_persona")
public class Estudiante extends Persona {

    @NotBlank
    @Column(name = "numero_matricula", unique = true)
    private String numeroMatricula;

    @NotBlank
    private String grado;
}
