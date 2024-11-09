package com.alibou.security.especialidad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
    Optional<Especialidad> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    List<Especialidad> findByEstadoTrue();
}
