package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Pago;
import com.example.gimnasio.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    @Operation(summary = "Lista todos los pagos")
    public ResponseEntity<List<Pago>> listar(){
        List<Pago> pagos = pagoService.listarTodos();
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/cliente/{id}")
    @Operation(summary = "Lista pagos por cliente(id)")
    public ResponseEntity<List<Pago>> listarPorCliente(@PathVariable Integer id){
        List<Pago> pagos = pagoService.listarPorCliente(id);
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca pagos por id")
    public ResponseEntity<Pago> buscarPorId(@PathVariable Integer id){
        return pagoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rango-fechas")
    @Operation(summary = "buscar por rangos de fecha")
    public ResponseEntity<List<Pago>> buscarPorRangoFechas(@RequestParam LocalDate inicio,
                                                           @RequestParam LocalDate fin){
        List<Pago> pagos = pagoService.buscarPorRangoFecha(inicio, fin);
        return ResponseEntity.ok(pagos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un pago por id")
    public ResponseEntity<Pago> actualizar(@PathVariable Integer id, @RequestBody Pago pago){
        try {
            Pago actualizado = pagoService.actualizarPago(id, pago);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registrar/{clienteId}")
    @Operation(summary = "Registra un nuevo pago por clienteId")
    public ResponseEntity<Pago> registrarPago(@PathVariable Integer clienteId, @RequestBody Pago pago){
        Pago pagoNuevo = pagoService.registrarPago(clienteId, pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoNuevo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina pago por id")
    public ResponseEntity<Pago> eliminar(@PathVariable Integer id){
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
