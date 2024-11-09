package com.alibou.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Contraseña Incorrecta");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("La contraseña no es Correcta");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }
    // Eliminar usuario
    public void deleteUser(Integer id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("Usuario no Encontrado" + id);
        }
        repository.deleteById(id);
    }

    // Método helper para convertir User a UserDTO
    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    // Métodos adicionales si los necesitas
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public UserDTO findByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario no tiene email: " + email));
        return convertToDTO(user);
    }
}
