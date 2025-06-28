package com.codesa.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long id;

    @NotBlank
    private String nombre;

    private String descripcion;

    @Min(1)
    private int creditos;

    @ManyToOne
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;
}
