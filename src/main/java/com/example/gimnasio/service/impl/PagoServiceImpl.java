package com.example.gimnasio.service.impl;

import com.example.gimnasio.entity.Pago;
import com.example.gimnasio.repository.PagoRepository;
import com.example.gimnasio.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    public PagoServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }

    @Override
    public List<Pago> listarPorCliente(Integer clienteId) {
        return pagoRepository.findByClienteClienteId(clienteId);
    }

    @Override
    public Optional<Pago> buscarPorId(Integer id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Pago guardar(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago actualizarPago(Integer id, Pago pagoActualizado) {
        return pagoRepository.findById(id)
                .map(pagoExistente -> {
                    pagoExistente.setFecha(pagoActualizado.getFecha());
                    pagoExistente.setMonto(pagoActualizado.getMonto());
                    pagoExistente.setEstado(pagoActualizado.getEstado());
                    return pagoRepository.save(pagoActualizado);
                }).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    @Override
    public void eliminar(Integer id) {
        pagoRepository.deleteById(id);
    }
}
