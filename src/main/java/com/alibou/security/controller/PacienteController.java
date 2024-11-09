package com.alibou.security.paciente;

import com.alibou.security.response.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    @Operation(summary = "Crear nuevo paciente")
    public ResponseEntity<CustomResponse> crearPaciente(@RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Paciente creado exitosamente")
                .status("CREATED")
                .data(pacienteService.crearPaciente(pacienteDTO))
                .build());
    }

    @GetMapping
    @Operation(summary = "Obtener todos los pacientes")
    public ResponseEntity<CustomResponse> obtenerTodos() {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Pacientes recuperados exitosamente")
                .status("OK")
                .data(pacienteService.obtenerTodos())
                .build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener paciente por ID")
    public ResponseEntity<CustomResponse> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Paciente recuperado exitosamente")
                .status("OK")
                .data(pacienteService.obtenerPorId(id))
                .build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar paciente")
    public ResponseEntity<CustomResponse> actualizarPaciente(
            @PathVariable Integer id,
            @RequestBody PacienteDTO pacienteDTO
    ) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Paciente actualizado exitosamente")
                .status("OK")
                .data(pacienteService.actualizarPaciente(id, pacienteDTO))
                .build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar paciente")
    public ResponseEntity<CustomResponse> eliminarPaciente(@PathVariable Integer id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Paciente eliminado exitosamente")
                .status("OK")
                .data(null)
                .build());
    }
}
