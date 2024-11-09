package com.alibou.security.horario;

import com.alibou.security.especialidad.Especialidad;
import com.alibou.security.medico.Medico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_horario_atencion")
public class HorarioAtencion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especialidad", nullable = false)
    private Especialidad especialidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Column(name = "dia_semana", nullable = false)
    private Integer diaSemana;  // 1=Lunes, 2=Martes, etc.

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Column(name = "nro_fichas_turno", nullable = false)
    private Integer nroFichasTurno;

    @Column(nullable = false)
    private Boolean estado = true;
}
