package com.alibou.security.especialidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private Integer idEspecialidad;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(length = 200)
    private String descripcion;

    @Column(name = "nro_fichas_diarias", nullable = false)
    private Integer nroFichasDiarias;

    @Column(name = "costo_consulta", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoConsulta;  // Cambiado de Double a BigDecimal para precisión decimal

    @Column(nullable = false)
    private Boolean estado = true;  // Valor por defecto true

    @Column(name = "duracion_consulta")
    private Integer duracionConsulta;  // En minutos
}
