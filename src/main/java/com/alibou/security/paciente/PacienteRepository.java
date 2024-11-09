package com.alibou.security.paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    Optional<Paciente> findByDni(String dni);
    boolean existsByDni(String dni);
    List<Paciente> findByEstadoTrue();
    List<Paciente> findByUsuarioId(Integer usuarioId);
}
