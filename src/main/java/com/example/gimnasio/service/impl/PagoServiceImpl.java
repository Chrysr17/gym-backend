package com.example.gimnasio.service.impl;

import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.entity.EstadoPago;
import com.example.gimnasio.entity.Pago;
import com.example.gimnasio.repository.ClienteRepository;
import com.example.gimnasio.repository.PagoRepository;
import com.example.gimnasio.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final ClienteRepository clienteRepository;

    public PagoServiceImpl(PagoRepository pagoRepository, ClienteRepository clienteRepository) {
        this.pagoRepository = pagoRepository;
        this.clienteRepository = clienteRepository;
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
    public List<Pago> buscarPorRangoFecha(LocalDate inicio, LocalDate fin) {
        return pagoRepository.findByFechaBetween(inicio, fin);
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
                    return pagoRepository.save(pagoExistente);
                }).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    @Override
    public Pago registrarPago(Integer clienteId, Pago pago) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no econtrado"));
        pago.setCliente(cliente);
        pago.setFecha(LocalDate.now());
        pago.setEstado(EstadoPago.Pagado);
        return pagoRepository.save(pago);
    }

    @Override
    public void eliminar(Integer id) {
        pagoRepository.deleteById(id);
    }
}
