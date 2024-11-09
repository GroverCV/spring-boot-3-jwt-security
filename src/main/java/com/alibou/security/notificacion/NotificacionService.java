package com.alibou.security.notificacion;

import com.alibou.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final UserRepository userRepository;

    @Transactional
    public NotificacionDTO crearNotificacion(NotificacionDTO notificacionDTO) {
        var user = userRepository.findById(notificacionDTO.getIdUsuarioDestino())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        var notificacion = Notificacion.builder()
                .titulo(notificacionDTO.getTitulo())
                .mensaje(notificacionDTO.getMensaje())
                .tipoNotificacion(notificacionDTO.getTipoNotificacion())
                .fechaCreacion(LocalDateTime.now())
                .usuarioDestino(user)
                .estadoLectura(false)
                .estado(true)
                .build();

        return convertToDTO(notificacionRepository.save(notificacion));
    }

    public List<NotificacionDTO> getNotificacionesUsuario(Integer usuarioId) {
        return notificacionRepository.findByUsuarioDestinoIdAndEstadoTrue(usuarioId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<NotificacionDTO> getNotificacionesNoLeidas(Integer usuarioId) {
        return notificacionRepository.findByUsuarioDestinoIdAndEstadoLecturaFalseAndEstadoTrue(usuarioId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public NotificacionDTO marcarComoLeida(Integer notificacionId) {
        var notificacion = notificacionRepository.findById(notificacionId)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        
        notificacion.setEstadoLectura(true);
        notificacion.setFechaLectura(LocalDateTime.now());
        
        return convertToDTO(notificacionRepository.save(notificacion));
    }

    @Transactional
    public void eliminarNotificacion(Integer notificacionId) {
        var notificacion = notificacionRepository.findById(notificacionId)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        
        notificacion.setEstado(false);
        notificacionRepository.save(notificacion);
    }

    private NotificacionDTO convertToDTO(Notificacion notificacion) {
        return NotificacionDTO.builder()
                .idNotificacion(notificacion.getIdNotificacion())
                .titulo(notificacion.getTitulo())
                .mensaje(notificacion.getMensaje())
                .tipoNotificacion(notificacion.getTipoNotificacion())
                .fechaCreacion(notificacion.getFechaCreacion())
                .fechaLectura(notificacion.getFechaLectura())
                .idUsuarioDestino(notificacion.getUsuarioDestino().getId())
                .estadoLectura(notificacion.getEstadoLectura())
                .estado(notificacion.getEstado())
                .build();
    }
}
