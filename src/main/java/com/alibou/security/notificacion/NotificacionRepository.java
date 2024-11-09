package com.alibou.security.notificacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByUsuarioDestinoIdAndEstadoTrue(Integer usuarioId);
    List<Notificacion> findByUsuarioDestinoIdAndEstadoLecturaFalseAndEstadoTrue(Integer usuarioId);
    long countByUsuarioDestinoIdAndEstadoLecturaFalseAndEstadoTrue(Integer usuarioId);
}
