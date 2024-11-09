package com.alibou.security.bitacora;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BitacoraDTO {
    private Integer idBitacora;
    private LocalDateTime fechaHora;
    private Integer usuarioId;
    private String tablaAfectada;
    private String tipoOperacion;
    private String valorAnterior;
    private String valorNuevo;
    private String ipUsuario;
    private String descripcion;
    private String tipoRegistro;
}
