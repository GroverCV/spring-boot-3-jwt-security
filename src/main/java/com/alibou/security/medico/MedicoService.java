package com.alibou.security.medico;

import com.alibou.security.especialidad.EspecialidadRepository;
import com.alibou.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;
    private final UserRepository userRepository;  // Agregado

    public MedicoDTO crearMedico(MedicoDTO medicoDTO) {
        if (medicoRepository.existsByDni(medicoDTO.getDni())) {
            throw new RuntimeException("Ya existe un médico con ese DNI");
        }

        var especialidad = especialidadRepository.findById(medicoDTO.getIdEspecialidad())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        var usuario = medicoDTO.getIdUsuario() != null ?
                userRepository.findById(medicoDTO.getIdUsuario())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")) :
                null;

        var medico = Medico.builder()
                .nombre(medicoDTO.getNombre())
                .apellidos(medicoDTO.getApellidos())
                .dni(medicoDTO.getDni())
                .telefono(medicoDTO.getTelefono())
                .especialidad(especialidad)
                .direccion(medicoDTO.getDireccion())
                .nroColegiatura(medicoDTO.getNroColegiatura())
                .horarioAtencion(medicoDTO.getHorarioAtencion())
                .fechaGraduacion(medicoDTO.getFechaGraduacion())
                .fechaIncorporacion(medicoDTO.getFechaIncorporacion() != null ?
                        medicoDTO.getFechaIncorporacion() : LocalDate.now())
                .usuario(usuario)
                .estado(true)
                .build();

        return convertToDTO(medicoRepository.save(medico));
    }



    public MedicoDTO actualizarMedico(Integer id, MedicoDTO medicoDTO) {
        var medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        if (!medico.getDni().equals(medicoDTO.getDni()) &&
                medicoRepository.existsByDni(medicoDTO.getDni())) {
            throw new RuntimeException("Ya existe un médico con ese DNI");
        }


        var especialidad = especialidadRepository.findById(medicoDTO.getIdEspecialidad())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        var usuario = medicoDTO.getIdUsuario() != null ?
                userRepository.findById(medicoDTO.getIdUsuario())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")) :
                medico.getUsuario();

        medico.setNombre(medicoDTO.getNombre());
        medico.setApellidos(medicoDTO.getApellidos());
        medico.setDni(medicoDTO.getDni());
        medico.setTelefono(medicoDTO.getTelefono());
        medico.setEspecialidad(especialidad);
        medico.setDireccion(medicoDTO.getDireccion());
        medico.setNroColegiatura(medicoDTO.getNroColegiatura());
        medico.setHorarioAtencion(medicoDTO.getHorarioAtencion());
        medico.setFechaGraduacion(medicoDTO.getFechaGraduacion());
        medico.setFechaIncorporacion(medicoDTO.getFechaIncorporacion());
        medico.setUsuario(usuario);

        return convertToDTO(medicoRepository.save(medico));
    }
    public List<MedicoDTO> obtenerTodos() {
        return medicoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MedicoDTO> obtenerActivos() {
        return medicoRepository.findByEstadoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MedicoDTO obtenerPorId(Integer id) {
        return medicoRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
    }

    public MedicoDTO obtenerPorDni(String dni) {
        return medicoRepository.findByDni(dni)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
    }

    public List<MedicoDTO> obtenerPorEspecialidad(Integer idEspecialidad) {
        return medicoRepository.findByEspecialidad_IdEspecialidadAndEstadoTrue(idEspecialidad)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MedicoDTO> obtenerPorUsuario(Integer idUsuario) {
        return medicoRepository.findByUsuarioId(idUsuario)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void eliminarMedico(Integer id) {
        var medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        medico.setEstado(false);
        medicoRepository.save(medico);
    }

    public MedicoDTO cambiarEstado(Integer id, Boolean estado) {
        var medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        medico.setEstado(estado);
        return convertToDTO(medicoRepository.save(medico));
    }

    public List<MedicoDTO> buscarPorNombreOApellidos(String termino) {
        return medicoRepository.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(termino, termino)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public boolean existePorDni(String dni) {
        return medicoRepository.existsByDni(dni);
    }
    private MedicoDTO convertToDTO(Medico medico) {
        return MedicoDTO.builder()
                .id(medico.getId())
                .nombre(medico.getNombre())
                .apellidos(medico.getApellidos())
                .dni(medico.getDni())
                .telefono(medico.getTelefono())
                .idEspecialidad(medico.getEspecialidad().getIdEspecialidad())
                .nombreEspecialidad(medico.getEspecialidad().getNombre())
                .direccion(medico.getDireccion())
                .nroColegiatura(medico.getNroColegiatura())
                .horarioAtencion(medico.getHorarioAtencion())
                .fechaGraduacion(medico.getFechaGraduacion())
                .fechaIncorporacion(medico.getFechaIncorporacion())
                .idUsuario(medico.getUsuario() != null ? medico.getUsuario().getId() : null)
                .estado(medico.getEstado())
                .build();
    }
}