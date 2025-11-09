package com.example.gimnasio.service;

import com.example.gimnasio.entity.Pago;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PagoService {
    List<Pago> listarTodos();
    List<Pago> listarPorCliente(Integer clienteId);
    List<Pago> buscarPorRangoFecha(LocalDate inicio, LocalDate fin);
    Optional<Pago> buscarPorId(Integer id);
    Pago guardar(Pago pago);
    Pago actualizarPago(Integer id, Pago pagoActualizado);
    Pago registrarPago(Integer clienteId, Pago pago);
    void eliminar(Integer id);
}