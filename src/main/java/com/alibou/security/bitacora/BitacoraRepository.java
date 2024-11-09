package com.alibou.security.bitacora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Integer> {
    List<Bitacora> findByUsuarioId(Integer usuarioId);
    List<Bitacora> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Bitacora> findByTablaAfectada(String tablaAfectada);
    List<Bitacora> findByTipoOperacion(String tipoOperacion);
}
