package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Sede;
import com.example.gimnasio.service.SedeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sedes")
public class SedeController {

    private final SedeService  sedeService;

    public SedeController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @GetMapping
    @Operation(summary = "Lista todas las sedes")
    public List<Sede> listar(){
        return sedeService.listarTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca una sede por Id")
    public ResponseEntity<Sede> buscarPorId(@PathVariable Integer id){
        return sedeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Guarda una sede")
    public ResponseEntity<Sede> guardar(@RequestBody Sede sede){
        Sede nueva = sedeService.guardar(sede);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping
    @Operation(summary = "Actualiza una sede por id")
    public ResponseEntity<Sede> actualizar(@PathVariable Integer id, @RequestBody Sede sede){
        try {
            Sede actualizada = sedeService.actualizarSede(id, sede);
            return ResponseEntity.ok(actualizada);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una sede por id")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        sedeService.buscarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
