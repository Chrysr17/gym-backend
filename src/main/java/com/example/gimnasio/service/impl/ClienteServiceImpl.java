package com.example.gimnasio.service.impl;

import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.repository.ClienteRepository;
import com.example.gimnasio.service.ClienteService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Optional<Cliente> buscarPorDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizarCliente(Integer id, Cliente clienteActualizado) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNombre(clienteActualizado.getNombre());
                    clienteExistente.setDni(clienteActualizado.getDni());
                    clienteExistente.setTelefono(clienteActualizado.getTelefono());
                    clienteExistente.setCorreo(clienteActualizado.getCorreo());
                    clienteExistente.setDireccion(clienteActualizado.getDireccion());
                    //clienteExistente.setSede(clienteActualizado.getSede());
                    clienteExistente.setFechaPago(clienteActualizado.getFechaPago());
                    clienteExistente.setMensualidad(clienteActualizado.getMensualidad());
                    clienteExistente.setDescripcion((clienteActualizado.getDescripcion()));
                    return clienteRepository.save(clienteExistente);
                }).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public void eliminar(Integer id) {
        clienteRepository.deleteById(id);
    }
}
