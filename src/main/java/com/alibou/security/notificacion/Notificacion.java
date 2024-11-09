package com.alibou.security.notificacion;

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
@Table(name = "_notificacion")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotificacion;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String mensaje;

    @Column(name = "tipo_notificacion", nullable = false, length = 50)
    private String tipoNotificacion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_lectura")
    private LocalDateTime fechaLectura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_destino", nullable = false)
    private User usuarioDestino;

    @Column(name = "estado_lectura", nullable = false)
    private Boolean estadoLectura = false;

    @Column(nullable = false)
    private Boolean estado = true;
}
