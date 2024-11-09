package com.alibou.security.notificacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionDTO {
    private Integer idNotificacion;
    private String titulo;
    private String mensaje;
    private String tipoNotificacion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaLectura;
    private Integer idUsuarioDestino;
    private Boolean estadoLectura;
    private Boolean estado;
}
