package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Cliente> buscarPorDni(@PathVariable String dni) {
        return clienteService.buscarPorDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente) {
        return clienteService.guardar(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return clienteService.buscarPorId(id)
                .map(existente -> {
                    existente.setNombre(cliente.getNombre());
                    existente.setDni(cliente.getDni());
                    existente.setTelefono(cliente.getTelefono());
                    existente.setCorreo(cliente.getCorreo());
                    existente.setDireccion(cliente.getDireccion());
                    existente.setSede(cliente.getSede());
                    existente.setFechaPago(cliente.getFechaPago());
                    existente.setMensualidad(cliente.getMensualidad());
                    existente.setDescripcion(cliente.getDescripcion());
                    return ResponseEntity.ok(clienteService.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
