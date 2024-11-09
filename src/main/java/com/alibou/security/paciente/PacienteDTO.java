package com.alibou.security.paciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {
    private Integer id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String genero;
    private String email;
    private String direccion;
    private String telefono;
    private String gravedadAfeccion;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaIngreso;
    private String ocupacion;
    private String religion;
    private String raza;
    private String lateralidad;
    private String informante;
    private String parentesco;
    private String confidencialidad;
    private Boolean estado;
    private Integer idUsuario;
}
