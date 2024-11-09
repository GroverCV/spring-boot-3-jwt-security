package com.alibou.security.demo;

import com.alibou.security.response.CustomResponse;
import com.alibou.security.token.TokenRepository;
import com.alibou.security.user.User;
import com.alibou.security.user.UserDTO;
import com.alibou.security.user.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/demo-controller")
@RequiredArgsConstructor
public class DemoController {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;

  @GetMapping
  public ResponseEntity<CustomResponse> sayHello() {
    return ResponseEntity.ok(CustomResponse.builder()
            .success(true)
            .message("Hello from secured endpoint")
            .status("OK")
            .data(null)
            .build());
  }

  @GetMapping("/users")
  public ResponseEntity<CustomResponse> getAllUsers() {
    List<UserDTO> userDTOs = userRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());

    return ResponseEntity.ok(CustomResponse.builder()
            .success(true)
            .message("Usuarios Listado Exitosamente")
            .status("OK")
            .data(userDTOs)
            .build());
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<CustomResponse> updateUser(
          @PathVariable Integer id,
          @RequestBody UserDTO userDTO
  ) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no Valido"));

    user.setFirstname(userDTO.getFirstname());
    user.setLastname(userDTO.getLastname());
    user.setEmail(userDTO.getEmail());
    user.setRole(userDTO.getRole());

    User updatedUser = userRepository.save(user);

    return ResponseEntity.ok(CustomResponse.builder()
            .success(true)
            .message("Usuario Actualizado Exitosamente")
            .status("OK")
            .data(convertToDTO(updatedUser))
            .build());
  }

  @DeleteMapping("/users/{id}")
  @Transactional
  public ResponseEntity<CustomResponse> deleteUser(@PathVariable Integer id) {
    if (!userRepository.existsById(id)) {
      return ResponseEntity.ok(CustomResponse.builder()
              .success(false)
              .message("Usuario no encontrado")
              .status("ERROR")
              .data(null)
              .build());
    }

    try {
      // Primero eliminamos los tokens asociados
      tokenRepository.deleteAllByUser_Id(id);

      // Luego eliminamos el usuario
      userRepository.deleteById(id);

      return ResponseEntity.ok(CustomResponse.builder()
              .success(true)
              .message("Usuario eliminado exitosamente")
              .status("OK")
              .data(null)
              .build());
    } catch (Exception e) {
      return ResponseEntity.ok(CustomResponse.builder()
              .success(false)
              .message("Error al eliminar usuario: " + e.getMessage())
              .status("ERROR")
              .data(null)
              .build());
    }
  }
  private UserDTO convertToDTO(User user) {
    return UserDTO.builder()
            .id(user.getId())
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .email(user.getEmail())
            .role(user.getRole())
            .build();
  }
}