package com.example.gimnasio.service;

import com.example.gimnasio.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listarTodos();
    Optional<Usuario> buscarPorId(Integer id);
    Optional<Usuario> buscarPorDni(String dni);
    Usuario guardar(Usuario usuario);
    Usuario actualizarCliente(Integer id, Usuario UsuarioActualizado);
    void eliminar(Integer id);

}
