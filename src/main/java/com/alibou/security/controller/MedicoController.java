package com.alibou.security.medico;

import com.alibou.security.response.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medicos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping
    @Operation(summary = "Crear nuevo médico")
    public ResponseEntity<CustomResponse> crearMedico(@RequestBody MedicoDTO medicoDTO) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Médico creado exitosamente")
                .status("CREATED")
                .data(medicoService.crearMedico(medicoDTO))
                .build());
    }

    @GetMapping
    @Operation(summary = "Obtener todos los médicos")
    public ResponseEntity<CustomResponse> obtenerTodos() {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Médicos recuperados exitosamente")
                .status("OK")
                .data(medicoService.obtenerTodos())
                .build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener médico por ID")
    public ResponseEntity<CustomResponse> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Médico recuperado exitosamente")
                .status("OK")
                .data(medicoService.obtenerPorId(id))
                .build());
    }

    @GetMapping("/especialidad/{idEspecialidad}")
    @Operation(summary = "Obtener médicos por especialidad")
    public ResponseEntity<CustomResponse> obtenerPorEspecialidad(@PathVariable Integer idEspecialidad) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Médicos por especialidad recuperados exitosamente")
                .status("OK")
                .data(medicoService.obtenerPorEspecialidad(idEspecialidad))
                .build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar médico")
    public ResponseEntity<CustomResponse> actualizarMedico(
            @PathVariable Integer id,
            @RequestBody MedicoDTO medicoDTO
    ) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Médico actualizado exitosamente")
                .status("OK")
                .data(medicoService.actualizarMedico(id, medicoDTO))
                .build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar médico")
    public ResponseEntity<CustomResponse> eliminarMedico(@PathVariable Integer id) {
        medicoService.eliminarMedico(id);
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Médico eliminado exitosamente")
                .status("OK")
                .data(null)
                .build());
    }
}
