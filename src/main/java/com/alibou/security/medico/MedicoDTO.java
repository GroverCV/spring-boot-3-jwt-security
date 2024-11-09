package com.alibou.security.medico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicoDTO {
    private Integer id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private Integer idEspecialidad;
    private String nombreEspecialidad;
    private String direccion;
    private String referenciaDireccion;  // Nuevo campo
    private String nroColegiatura;
    private String horarioAtencion;
    private LocalDate fechaGraduacion;
    private LocalDate fechaIncorporacion;
    private String observaciones;  // Nuevo campo
    private Boolean estado;
    private Integer idUsuario;
}
