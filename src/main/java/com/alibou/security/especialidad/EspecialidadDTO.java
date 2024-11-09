package com.alibou.security.especialidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EspecialidadDTO {
    private Integer idEspecialidad;
    private String nombre;
    private String descripcion;
    private Integer nroFichasDiarias;
    private BigDecimal costoConsulta;
    private Boolean estado;
    private Integer duracionConsulta;
}
