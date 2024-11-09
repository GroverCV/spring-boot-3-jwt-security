package com.alibou.security.controller;

import com.alibou.security.notificacion.NotificacionDTO;
import com.alibou.security.notificacion.NotificacionService;
import com.alibou.security.response.CustomResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notificaciones")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<CustomResponse> crearNotificacion(@RequestBody NotificacionDTO notificacionDTO) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Notificación creada exitosamente")
                .status("CREATED")
                .data(notificacionService.crearNotificacion(notificacionDTO))
                .build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CustomResponse> getNotificacionesUsuario(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Notificaciones recuperadas exitosamente")
                .status("OK")
                .data(notificacionService.getNotificacionesUsuario(usuarioId))
                .build());
    }

    @GetMapping("/no-leidas/{usuarioId}")
    public ResponseEntity<CustomResponse> getNotificacionesNoLeidas(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Notificaciones no leídas recuperadas exitosamente")
                .status("OK")
                .data(notificacionService.getNotificacionesNoLeidas(usuarioId))
                .build());
    }

    @PatchMapping("/marcar-leida/{notificacionId}")
    public ResponseEntity<CustomResponse> marcarComoLeida(@PathVariable Integer notificacionId) {
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Notificación marcada como leída exitosamente")
                .status("OK")
                .data(notificacionService.marcarComoLeida(notificacionId))
                .build());
    }

    @DeleteMapping("/{notificacionId}")
    public ResponseEntity<CustomResponse> eliminarNotificacion(@PathVariable Integer notificacionId) {
        notificacionService.eliminarNotificacion(notificacionId);
        return ResponseEntity.ok(CustomResponse.builder()
                .success(true)
                .message("Notificación eliminada exitosamente")
                .status("OK")
                .data(null)
                .build());
    }
}

