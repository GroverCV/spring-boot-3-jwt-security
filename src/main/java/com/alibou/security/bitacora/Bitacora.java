package com.alibou.security.bitacora;

import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_bitacora")
public class Bitacora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBitacora;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @Column(name = "tabla_afectada", nullable = false, length = 100)
    private String tablaAfectada;

    @Column(name = "tipo_operacion", nullable = false, length = 20)
    private String tipoOperacion;

    @Column(name = "valor_anterior", columnDefinition = "TEXT")
    private String valorAnterior;

    @Column(name = "valor_nuevo", columnDefinition = "TEXT")
    private String valorNuevo;

    @Column(name = "ip_usuario", length = 50)
    private String ipUsuario;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "tipo_registro", length = 50)
    private String tipoRegistro;
}
