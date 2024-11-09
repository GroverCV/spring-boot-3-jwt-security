package com.alibou.security.horario;

import com.alibou.security.especialidad.EspecialidadRepository;
import com.alibou.security.medico.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HorarioAtencionService {

    private final HorarioAtencionRepository horarioRepository;
    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;

    public HorarioAtencionDTO crearHorario(HorarioAtencionDTO horarioDTO) {
        var medico = medicoRepository.findById(horarioDTO.getIdMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        var especialidad = especialidadRepository.findById(horarioDTO.getIdEspecialidad())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        // Validar que el horario no se solape
        if (horarioRepository.existsByMedicoIdAndDiaSemanaAndHoraInicioAndHoraFin(
                horarioDTO.getIdMedico(), 
                horarioDTO.getDiaSemana(), 
                horarioDTO.getHoraInicio(), 
                horarioDTO.getHoraFin())) {
            throw new RuntimeException("Ya existe un horario para este médico en este día y hora");
        }

        var horario = HorarioAtencion.builder()
                .medico(medico)
                .especialidad(especialidad)
                .diaSemana(horarioDTO.getDiaSemana())
                .horaInicio(horarioDTO.getHoraInicio())
                .horaFin(horarioDTO.getHoraFin())
                .nroFichasTurno(horarioDTO.getNroFichasTurno())
                .estado(true)
                .build();

        return convertToDTO(horarioRepository.save(horario));
    }

    public List<HorarioAtencionDTO> obtenerPorMedico(Integer idMedico) {
        return horarioRepository.findByMedicoIdAndEstadoTrue(idMedico)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HorarioAtencionDTO> obtenerPorEspecialidad(Integer idEspecialidad) {
        return horarioRepository.findByEspecialidadIdEspecialidadAndEstadoTrue(idEspecialidad)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HorarioAtencionDTO> obtenerPorDia(Integer diaSemana) {
        return horarioRepository.findByDiaSemanaAndEstadoTrue(diaSemana)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public HorarioAtencionDTO actualizarHorario(Integer id, HorarioAtencionDTO horarioDTO) {
        var horario = horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        var medico = medicoRepository.findById(horarioDTO.getIdMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        var especialidad = especialidadRepository.findById(horarioDTO.getIdEspecialidad())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        horario.setMedico(medico);
        horario.setEspecialidad(especialidad);
        horario.setDiaSemana(horarioDTO.getDiaSemana());
        horario.setHoraInicio(horarioDTO.getHoraInicio());
        horario.setHoraFin(horarioDTO.getHoraFin());
        horario.setNroFichasTurno(horarioDTO.getNroFichasTurno());

        return convertToDTO(horarioRepository.save(horario));
    }

    public void eliminarHorario(Integer id) {
        var horario = horarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
        horario.setEstado(false);
        horarioRepository.save(horario);
    }

    private HorarioAtencionDTO convertToDTO(HorarioAtencion horario) {
        return HorarioAtencionDTO.builder()
                .id(horario.getId())
                .idEspecialidad(horario.getEspecialidad().getIdEspecialidad())
                .nombreEspecialidad(horario.getEspecialidad().getNombre())
                .idMedico(horario.getMedico().getId())
                .nombreMedico(horario.getMedico().getNombre() + " " + horario.getMedico().getApellidos())
                .diaSemana(horario.getDiaSemana())
                .horaInicio(horario.getHoraInicio())
                .horaFin(horario.getHoraFin())
                .nroFichasTurno(horario.getNroFichasTurno())
                .estado(horario.getEstado())
                .build();
    }
}
