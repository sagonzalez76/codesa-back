package com.codesa.backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionDTO {

    private Long id;

    @NotNull(message = "Debe seleccionarse un estudiante.")
    private Long idEstudiante;

    @NotNull(message = "Debe seleccionarse un curso.")
    private Long idCurso;

    @PastOrPresent(message = "La fecha de inscripción no puede ser futura.")
    private LocalDate fechaInscripcion;

    // Métodos adicionales para compatibilidad con nombres alternativos
    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }
}
