package com.alibou.security.bitacora;

import com.alibou.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BitacoraService {

    private final BitacoraRepository bitacoraRepository;
    private final UserRepository userRepository;

    public BitacoraDTO registrarOperacion(BitacoraDTO bitacoraDTO) {
        Bitacora bitacora = Bitacora.builder()
                .fechaHora(LocalDateTime.now())
                .usuario(userRepository.findById(bitacoraDTO.getUsuarioId())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado")))
                .tablaAfectada(bitacoraDTO.getTablaAfectada())
                .tipoOperacion(bitacoraDTO.getTipoOperacion())
                .valorAnterior(bitacoraDTO.getValorAnterior())
                .valorNuevo(bitacoraDTO.getValorNuevo())
                .ipUsuario(bitacoraDTO.getIpUsuario())
                .descripcion(bitacoraDTO.getDescripcion())
                .tipoRegistro(bitacoraDTO.getTipoRegistro())
                .build();

        return convertToDTO(bitacoraRepository.save(bitacora));
    }

    public List<BitacoraDTO> obtenerRegistros() {
        return bitacoraRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<BitacoraDTO> obtenerPorUsuario(Integer usuarioId) {
        List<Bitacora> bitacoras = bitacoraRepository.findByUsuarioId(usuarioId);
        return bitacoras.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BitacoraDTO> obtenerPorTipoOperacion(String tipoOperacion) {
        List<Bitacora> bitacoras = bitacoraRepository.findByTipoOperacion(tipoOperacion);
        return bitacoras.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BitacoraDTO convertToDTO(Bitacora bitacora) {
        return BitacoraDTO.builder()
                .idBitacora(bitacora.getIdBitacora())
                .fechaHora(bitacora.getFechaHora())
                .usuarioId(bitacora.getUsuario().getId())
                .tablaAfectada(bitacora.getTablaAfectada())
                .tipoOperacion(bitacora.getTipoOperacion())
                .valorAnterior(bitacora.getValorAnterior())
                .valorNuevo(bitacora.getValorNuevo())
                .ipUsuario(bitacora.getIpUsuario())
                .descripcion(bitacora.getDescripcion())
                .tipoRegistro(bitacora.getTipoRegistro())
                .build();
    }
}
