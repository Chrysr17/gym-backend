package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Pago;
import com.example.gimnasio.service.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public List<Pago> listar(){
        return pagoService.listarTodos();
    }

    @GetMapping("/cliente/{id}")
    public List<Pago> listarPorCliente(@PathVariable Integer id){
        return pagoService.listarPorCliente(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscarPorId(@PathVariable Integer id){
        return pagoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizar(@PathVariable Integer id, @RequestBody Pago pago){
        try {
            Pago actualizado = pagoService.actualizarPago(id, pago);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pago> eliminar(@PathVariable Integer id){
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
