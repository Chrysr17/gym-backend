package com.example.gimnasio.service;

import com.example.gimnasio.entity.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoService {
    List<Pago> listarTodos();
    List<Pago> listarPorCliente(Integer clienteId);
    Optional<Pago> buscarPorId(Integer id);
    Pago guardar(Pago pago);
    void eliminar(Integer id);
}