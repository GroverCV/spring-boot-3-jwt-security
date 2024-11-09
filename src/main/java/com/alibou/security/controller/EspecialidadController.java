package com.alibou.security.controller;

import com.alibou.security.especialidad.EspecialidadDTO;
import com.alibou.security.especialidad.EspecialidadService;
import com.alibou.security.response.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/especialidades")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    @Operation(summary = "Crear nueva especialidad")
    public ResponseEntity<CustomResponse> crearEspecialidad(@RequestBody EspecialidadDTO especialidadDTO) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Especialidad creada exitosamente")
                .status("CREATED")
                .data(especialidadService.crearEspecialidad(especialidadDTO))
                .build());
    }

    @GetMapping
    @Operation(summary = "Obtener todas las especialidades")
    public ResponseEntity<CustomResponse> obtenerTodas() {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Especialidades recuperadas exitosamente")
                .status("OK")
                .data(especialidadService.obtenerTodas())
                .build());
    }

    @GetMapping("/activas")
    @Operation(summary = "Obtener especialidades activas")
    public ResponseEntity<CustomResponse> obtenerActivas() {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Especialidades activas recuperadas exitosamente")
                .status("OK")
                .data(especialidadService.obtenerActivas())
                .build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener especialidad por ID")
    public ResponseEntity<CustomResponse> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Especialidad recuperada exitosamente")
                .status("OK")
                .data(especialidadService.obtenerPorId(id))
                .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    @Operation(summary = "Actualizar especialidad")
    public ResponseEntity<CustomResponse> actualizarEspecialidad(
            @PathVariable Integer id,
            @RequestBody EspecialidadDTO especialidadDTO
    ) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Especialidad actualizada exitosamente")
                .status("OK")
                .data(especialidadService.actualizarEspecialidad(id, especialidadDTO))
                .build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    @Operation(summary = "Eliminar especialidad")
    public ResponseEntity<CustomResponse> eliminarEspecialidad(@PathVariable Integer id) {
        especialidadService.eliminarEspecialidad(id);
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Especialidad eliminada exitosamente")
                .status("OK")
                .data(null)
                .build());
    }
}
