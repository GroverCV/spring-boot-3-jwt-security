package com.alibou.security.especialidad;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EspecialidadService {

    private final EspecialidadRepository repository;


    public EspecialidadDTO crearEspecialidad(EspecialidadDTO especialidadDTO) {
        if (repository.existsByNombre(especialidadDTO.getNombre())) {
            throw new RuntimeException("Ya existe una especialidad con ese nombre");
        }

        Especialidad especialidad = Especialidad.builder()
                .nombre(especialidadDTO.getNombre())
                .descripcion(especialidadDTO.getDescripcion())
                .nroFichasDiarias(especialidadDTO.getNroFichasDiarias())
                .costoConsulta(especialidadDTO.getCostoConsulta())
                .estado(true)
                .duracionConsulta(especialidadDTO.getDuracionConsulta())
                .build();

        return convertToDTO(repository.save(especialidad));
    }

    public List<EspecialidadDTO> obtenerTodas() {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EspecialidadDTO> obtenerActivas() {
        return repository.findByEstadoTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EspecialidadDTO obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
    }

    public EspecialidadDTO actualizarEspecialidad(Integer id, EspecialidadDTO especialidadDTO) {
        Especialidad especialidad = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));

        if (!especialidad.getNombre().equals(especialidadDTO.getNombre()) &&
            repository.existsByNombre(especialidadDTO.getNombre())) {
            throw new RuntimeException("Ya existe una especialidad con ese nombre");
        }

        especialidad.setNombre(especialidadDTO.getNombre());
        especialidad.setDescripcion(especialidadDTO.getDescripcion());
        especialidad.setNroFichasDiarias(especialidadDTO.getNroFichasDiarias());
        especialidad.setCostoConsulta(especialidadDTO.getCostoConsulta());
        especialidad.setDuracionConsulta(especialidadDTO.getDuracionConsulta());

        return convertToDTO(repository.save(especialidad));
    }

    public void eliminarEspecialidad(Integer id) {
        Especialidad especialidad = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        especialidad.setEstado(false);
        repository.save(especialidad);
    }

    private EspecialidadDTO convertToDTO(Especialidad especialidad) {
        return EspecialidadDTO.builder()
                .idEspecialidad(especialidad.getIdEspecialidad())
                .nombre(especialidad.getNombre())
                .descripcion(especialidad.getDescripcion())
                .nroFichasDiarias(especialidad.getNroFichasDiarias())
                .costoConsulta(especialidad.getCostoConsulta())
                .estado(especialidad.getEstado())
                .duracionConsulta(especialidad.getDuracionConsulta())
                .build();
    }
}
