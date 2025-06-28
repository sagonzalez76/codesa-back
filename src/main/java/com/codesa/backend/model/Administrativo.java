package com.codesa.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "administrativo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id_persona")

public class Administrativo extends Persona {

    @NotBlank
    private String cargo;

    @NotBlank
    private String departamento;
}
