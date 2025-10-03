package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Sede;
import com.example.gimnasio.service.SedeService;
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
    public List<Sede> listar(){
        return sedeService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sede> buscarPorId(@PathVariable Integer id){
        return sedeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sede> guardar(@RequestBody Sede sede){
        Sede nueva = sedeService.guardar(sede);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping
    public ResponseEntity<Sede> actualziar(@PathVariable Integer id, @RequestBody Sede sede){
        try {
            Sede actualizada = sedeService.actualizarSede(id, sede);
            return ResponseEntity.ok(actualizada);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        sedeService.buscarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
