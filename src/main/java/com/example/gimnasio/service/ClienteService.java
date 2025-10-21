package com.example.gimnasio.service;

import com.example.gimnasio.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarTodos();
    List<Cliente> buscarPorNombre(String nombre);
    Optional<Cliente> buscarPorId(Integer id);
    Optional<Cliente> buscarPorDni(String dni);
    Cliente guardar(Cliente cliente);
    Cliente actualizarCliente(Integer id, Cliente clienteActualizado);
    void eliminarLogico(Integer id);
    void restaurar(Integer id);
    List<Cliente> listarPorSede(Integer sedeId);
    List<Cliente> listarEliminados();
}
