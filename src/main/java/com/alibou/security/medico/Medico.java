package com.alibou.security.medico;

import com.alibou.security.especialidad.Especialidad;
import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 20)
    private String dni;

    @Column(length = 20)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especialidad", nullable = false)
    private Especialidad especialidad;

    @Column(length = 500)  // Aumentado de 200 a 500
    private String direccion;  // Para direcciones más completas

    @Column(name = "nro_colegiatura", length = 50)  // Aumentado de 20 a 50
    private String nroColegiatura;

    @Column(name = "horario_atencion", columnDefinition = "TEXT")  // Cambiado a TEXT para más flexibilidad
    private String horarioAtencion;

    @Column(name = "fecha_graduacion")
    private LocalDate fechaGraduacion;

    @Column(name = "fecha_incorporacion", nullable = false)
    private LocalDate fechaIncorporacion;

    @Column(columnDefinition = "TEXT")
    private String observaciones;  // Nuevo campo para notas adicionales

    @Column(length = 300)
    private String referenciaDireccion;  // Nuevo campo para referencias de la dirección

    @Column(nullable = false)
    private Boolean estado = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private User usuario;
}
