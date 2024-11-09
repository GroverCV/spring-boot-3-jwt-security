package com.alibou.security.medico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    Optional<Medico> findByDni(String dni);
    boolean existsByDni(String dni);
    List<Medico> findByEstadoTrue();
    List<Medico> findByEspecialidad_IdEspecialidadAndEstadoTrue(Integer idEspecialidad);
    List<Medico> findByUsuarioId(Integer usuarioId);
    List<Medico> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCase(
            String nombre, String apellidos);}
