package com.example.gimnasio.service;

import com.example.gimnasio.entity.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorService {
    List<Proveedor> listarTodos();
    Optional<Proveedor> buscarPorId(Integer id);
    Proveedor guardar(Proveedor proveedor);
    Proveedor actualizar(Integer id, Proveedor proveedorActualizado);
    void eliminar(Integer id);
    List<Proveedor> listarPorCategoria(String categoria);
    List<Proveedor> buscarPorNombreYCategoria(String nombre, String categoria);
}
