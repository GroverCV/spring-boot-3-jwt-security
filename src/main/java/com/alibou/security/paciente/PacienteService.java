package com.alibou.security.paciente;

import com.alibou.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final UserRepository userRepository;

    public PacienteDTO crearPaciente(PacienteDTO pacienteDTO) {
        if (pacienteRepository.existsByDni(pacienteDTO.getDni())) {
            throw new RuntimeException("Ya existe un paciente con ese DNI");
        }

        var usuario = pacienteDTO.getIdUsuario() != null ?
                userRepository.findById(pacienteDTO.getIdUsuario())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")) :
                null;

        var paciente = Paciente.builder()
                .nombre(pacienteDTO.getNombre())
                .apellidos(pacienteDTO.getApellidos())
                .dni(pacienteDTO.getDni())
                .genero(pacienteDTO.getGenero())
                .email(pacienteDTO.getEmail())
                .direccion(pacienteDTO.getDireccion())
                .telefono(pacienteDTO.getTelefono())
                .gravedadAfeccion(pacienteDTO.getGravedadAfeccion())
                .fechaNacimiento(pacienteDTO.getFechaNacimiento())
                .fechaIngreso(LocalDateTime.now())
                .ocupacion(pacienteDTO.getOcupacion())
                .religion(pacienteDTO.getReligion())
                .raza(pacienteDTO.getRaza())
                .lateralidad(pacienteDTO.getLateralidad())
                .informante(pacienteDTO.getInformante())
                .parentesco(pacienteDTO.getParentesco())
                .confidencialidad(pacienteDTO.getConfidencialidad())
                .estado(true)
                .usuario(usuario)
                .build();

        return convertToDTO(pacienteRepository.save(paciente));
    }

    public List<PacienteDTO> obtenerTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PacienteDTO obtenerPorId(Integer id) {
        return pacienteRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    public PacienteDTO actualizarPaciente(Integer id, PacienteDTO pacienteDTO) {
        var paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if (!paciente.getDni().equals(pacienteDTO.getDni()) &&
            pacienteRepository.existsByDni(pacienteDTO.getDni())) {
            throw new RuntimeException("Ya existe un paciente con ese DNI");
        }

        var usuario = pacienteDTO.getIdUsuario() != null ?
                userRepository.findById(pacienteDTO.getIdUsuario())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")) :
                paciente.getUsuario();

        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setApellidos(pacienteDTO.getApellidos());
        paciente.setDni(pacienteDTO.getDni());
        paciente.setGenero(pacienteDTO.getGenero());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setDireccion(pacienteDTO.getDireccion());
        paciente.setTelefono(pacienteDTO.getTelefono());
        paciente.setGravedadAfeccion(pacienteDTO.getGravedadAfeccion());
        paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
        paciente.setOcupacion(pacienteDTO.getOcupacion());
        paciente.setReligion(pacienteDTO.getReligion());
        paciente.setRaza(pacienteDTO.getRaza());
        paciente.setLateralidad(pacienteDTO.getLateralidad());
        paciente.setInformante(pacienteDTO.getInformante());
        paciente.setParentesco(pacienteDTO.getParentesco());
        paciente.setConfidencialidad(pacienteDTO.getConfidencialidad());
        paciente.setUsuario(usuario);

        return convertToDTO(pacienteRepository.save(paciente));
    }

    public void eliminarPaciente(Integer id) {
        var paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.setEstado(false);
        pacienteRepository.save(paciente);
    }

    private PacienteDTO convertToDTO(Paciente paciente) {
        return PacienteDTO.builder()
                .id(paciente.getId())
                .nombre(paciente.getNombre())
                .apellidos(paciente.getApellidos())
                .dni(paciente.getDni())
                .genero(paciente.getGenero())
                .email(paciente.getEmail())
                .direccion(paciente.getDireccion())
                .telefono(paciente.getTelefono())
                .gravedadAfeccion(paciente.getGravedadAfeccion())
                .fechaNacimiento(paciente.getFechaNacimiento())
                .fechaIngreso(paciente.getFechaIngreso())
                .ocupacion(paciente.getOcupacion())
                .religion(paciente.getReligion())
                .raza(paciente.getRaza())
                .lateralidad(paciente.getLateralidad())
                .informante(paciente.getInformante())
                .parentesco(paciente.getParentesco())
                .confidencialidad(paciente.getConfidencialidad())
                .estado(paciente.getEstado())
                .idUsuario(paciente.getUsuario() != null ? paciente.getUsuario().getId() : null)
                .build();
    }
}
