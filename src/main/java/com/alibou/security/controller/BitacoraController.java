package com.alibou.security.controller;

import com.alibou.security.bitacora.BitacoraDTO;
import com.alibou.security.bitacora.BitacoraService;
import com.alibou.security.response.CustomResponse;  // Importación correcta
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bitacora")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Bitacora")
public class BitacoraController {

    private final BitacoraService bitacoraService;

    @PostMapping
    @Operation(summary = "Registrar operación en bitácora")
    public ResponseEntity<CustomResponse> registrarOperacion(@RequestBody BitacoraDTO bitacoraDTO) {
        bitacoraService.registrarOperacion(bitacoraDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Registro guardado exitosamente en bitácora")
                .status("CREATED")
                .data(null)
                .build());
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getAllBitacoras() {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Registros recuperados exitosamente")
                .status("OK")
                .data(bitacoraService.obtenerRegistros())
                .build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CustomResponse> getBitacorasByUsuario(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Registros por usuario recuperados exitosamente")
                .status("OK")
                .data(bitacoraService.obtenerPorUsuario(usuarioId))
                .build());
    }
}