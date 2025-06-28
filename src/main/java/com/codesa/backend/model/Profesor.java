package com.codesa.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "profesor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id_persona")
public class Profesor extends Persona {

    @NotBlank
    private String especialidad;

    @PastOrPresent
    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;
}
