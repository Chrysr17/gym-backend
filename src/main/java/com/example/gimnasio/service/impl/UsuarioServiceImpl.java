package com.example.gimnasio.service.impl;

import com.example.gimnasio.entity.Usuario;
import com.example.gimnasio.repository.UsuarioRepository;
import com.example.gimnasio.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNombreUsuario(usuarioActualizado.getNombreUsuario());
                    usuarioExistente.setRol(usuarioActualizado.getRol());
                    if (usuarioActualizado.getPassword() != null
                            && !usuarioActualizado.getPassword().isBlank()) {
                        usuarioExistente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
                    }
                    return usuarioRepository.save(usuarioExistente);
                }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
