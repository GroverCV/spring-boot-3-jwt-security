package com.alibou.security.horario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface HorarioAtencionRepository extends JpaRepository<HorarioAtencion, Integer> {
    List<HorarioAtencion> findByMedicoIdAndEstadoTrue(Integer idMedico);

    List<HorarioAtencion> findByEspecialidadIdEspecialidadAndEstadoTrue(Integer idEspecialidad);

    List<HorarioAtencion> findByDiaSemanaAndEstadoTrue(Integer diaSemana);

    boolean existsByMedicoIdAndDiaSemanaAndHoraInicioAndHoraFin(
            Integer idMedico,
            Integer diaSemana,
            LocalTime horaInicio,
            LocalTime horaFin);
}
