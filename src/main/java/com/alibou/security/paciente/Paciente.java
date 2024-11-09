package com.alibou.security.paciente;

import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 20)
    private String dni;

    @Column(length = 10)
    private String genero;

    @Column(length = 100)
    private String email;

    @Column(length = 200)
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @Column(name = "gravedad_afeccion", length = 20)
    private String gravedadAfeccion;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    @Column(length = 200)
    private String ocupacion;

    @Column(length = 200)
    private String religion;

    @Column(length = 200)
    private String raza;

    @Column(length = 200)
    private String lateralidad;

    @Column(length = 200)
    private String informante;

    @Column(length = 200)
    private String parentesco;

    @Column(length = 200)
    private String confidencialidad;

    @Column(nullable = false)
    private Boolean estado = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private User usuario;
}
