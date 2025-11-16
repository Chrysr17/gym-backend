package com.example.gimnasio.controller;

import com.example.gimnasio.dto.LoginRequest;
import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.entity.Rol;
import com.example.gimnasio.entity.Usuario;
import com.example.gimnasio.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreUsuario(request.getNombreUsuario());

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        Usuario usuario = usuarioOptional.get();
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.badRequest().body("Contraseña incorrecta");
        }

        if (usuario.getRol() == Rol.CLIENTE) {
            Cliente cliente = usuario.getCliente();
            if (cliente != null) {
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.badRequest().body("Cliente no asociado al usuario");
            }
        }
        return ResponseEntity.ok(usuario);
    }
}
