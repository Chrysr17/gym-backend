package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.entity.Rol;
import com.example.gimnasio.entity.Usuario;
import com.example.gimnasio.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String nombreUsuario,
                                   @RequestParam String password) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByNombreUsuario(nombreUsuario);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (usuario.getPassword().equals(password)) {
                if (usuario.getRol() == Rol.CLIENTE) {
                    Cliente cliente = usuario.getCliente();
                    if (cliente != null) {
                        return ResponseEntity.ok(cliente);
                    } else {
                        return ResponseEntity.badRequest().body("Cliente no asociado al usuario");
                    }
                } else {
                    return ResponseEntity.ok(usuario);
                }
            } else {
                return ResponseEntity.badRequest().body("Contraseña incorrecta");
            }
        } else {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }
    }
}
