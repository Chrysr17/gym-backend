package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @Operation(summary = "Lista todos los clientes")
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/eliminados")
    @Operation(summary = "Lista todos los clientes incluyendo los eliminados")
    public ResponseEntity<List<Cliente>> listarEliminados(){
        List<Cliente> clientes = clienteService.listarEliminados();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca por id")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dni/{dni}")
    @Operation(summary = "Buscar por dni")
    public ResponseEntity<Cliente> buscarPorDni(@PathVariable String dni) {
        return clienteService.buscarPorDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Guarda un nuevo cliente")
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) {
        Cliente nuevo = clienteService.guardar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un cliente por id")
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            Cliente actualizado = clienteService.actualizarCliente(id, cliente);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina de manera lógica un cliente por id")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        clienteService.eliminarLogico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sede/{sedeId}")
    @Operation(summary = "Lista por sedeId")
    public List<Cliente> listarPorSede(@PathVariable Integer sedeId) {
        return clienteService.listarPorSede(sedeId);
    }

    @PutMapping("/restaurar/{id}")
    @Operation(summary = "Restaura un cliente eliminado")
    public ResponseEntity<Void> restaurar(@PathVariable Integer id){
        clienteService.restaurar(id);
        return ResponseEntity.noContent().build();
    }
}
