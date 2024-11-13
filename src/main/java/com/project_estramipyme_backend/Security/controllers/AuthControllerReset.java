package com.project_estramipyme_backend.Security.controllers;

import com.project_estramipyme_backend.Security.security.JwtUtilRec;
import com.project_estramipyme_backend.user.model.UserModel;
import com.project_estramipyme_backend.user.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthControllerReset {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtUtilRec jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @RequestBody String newPassword) {
        // Verifica que el token es válido
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }

        // Extrae el email del token
        String email = jwtUtil.extractUsername(token);

        // Busca al usuario por su email
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Codifica la nueva contraseña
        user.setPassword(passwordEncoder.encode(newPassword));

        // Guarda la nueva contraseña en la base de datos
        userRepository.save(user);

        return ResponseEntity.ok("Contraseña restablecida correctamente");
    }
}
