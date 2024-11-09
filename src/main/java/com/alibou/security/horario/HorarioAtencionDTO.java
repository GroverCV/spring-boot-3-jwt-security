package com.alibou.security.horario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HorarioAtencionDTO {
    private Integer id;
    private Integer idEspecialidad;
    private String nombreEspecialidad;
    private Integer idMedico;
    private String nombreMedico;
    private Integer diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Integer nroFichasTurno;
    private Boolean estado;

    // Helper method para obtener el nombre del día
    public String getNombreDiaSemana() {
        return switch (diaSemana) {
            case 1 -> "Lunes";
            case 2 -> "Martes";
            case 3 -> "Miércoles";
            case 4 -> "Jueves";
            case 5 -> "Viernes";
            case 6 -> "Sábado";
            case 7 -> "Domingo";
            default -> "Día inválido";
        };
    }
}
