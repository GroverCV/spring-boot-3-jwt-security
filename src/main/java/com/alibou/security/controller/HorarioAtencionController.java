package com.alibou.security.controller;

import com.alibou.security.horario.HorarioAtencionDTO;
import com.alibou.security.horario.HorarioAtencionService;
import com.alibou.security.response.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/horarios")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")

public class HorarioAtencionController {

    private final HorarioAtencionService horarioAtencionService;

    @PostMapping
    @Operation(summary = "Crear nuevo horario de atención")
    public ResponseEntity<CustomResponse> crearHorario(@RequestBody HorarioAtencionDTO horarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomResponse.builder()
                .success(true)
                .message("Horario de atención creado exitosamente")
                .status("CREATED")
                .data(horarioAtencionService.crearHorario(horarioDTO))
                .build());
    }

    @GetMapping("/medico/{idMedico}")
    @Operation(summary = "Obtener horarios de atención por médico")
    public ResponseEntity<CustomResponse> obtenerPorMedico(@PathVariable Integer idMedico) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Horarios de atención por médico recuperados exitosamente")
                .status("OK")
                .data(horarioAtencionService.obtenerPorMedico(idMedico))
                .build());
    }

    @GetMapping("/especialidad/{idEspecialidad}")
    @Operation(summary = "Obtener horarios de atención por especialidad")
    public ResponseEntity<CustomResponse> obtenerPorEspecialidad(@PathVariable Integer idEspecialidad) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Horarios de atención por especialidad recuperados exitosamente")
                .status("OK")
                .data(horarioAtencionService.obtenerPorEspecialidad(idEspecialidad))
                .build());
    }

    @GetMapping("/dia/{diaSemana}")
    @Operation(summary = "Obtener horarios de atención por día de la semana")
    public ResponseEntity<CustomResponse> obtenerPorDia(@PathVariable Integer diaSemana) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Horarios de atención por día de la semana recuperados exitosamente")
                .status("OK")
                .data(horarioAtencionService.obtenerPorDia(diaSemana))
                .build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar horario de atención")
    public ResponseEntity<CustomResponse> actualizarHorario(
            @PathVariable Integer id,
            @RequestBody HorarioAtencionDTO horarioDTO
    ) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Horario de atención actualizado exitosamente")
                .status("OK")
                .data(horarioAtencionService.actualizarHorario(id, horarioDTO))
                .build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar horario de atención")
    public ResponseEntity<CustomResponse> eliminarHorario(@PathVariable Integer id) {
        horarioAtencionService.eliminarHorario(id);
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Horario de atención eliminado exitosamente")
                .status("OK")
                .data(null)
                .build());
    }
}