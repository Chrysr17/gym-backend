package com.example.gimnasio.service;

import com.example.gimnasio.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarTodos();
    Optional<Cliente> buscarPorId(Integer id);
    Optional<Cliente> buscarPorDni(String dni);
    Cliente guardar(Cliente cliente);
    void eliminar(Integer id);
}
