package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Maquina;
import com.example.gimnasio.service.MaquinaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {

    private final MaquinaService maquinaService;

    public MaquinaController(MaquinaService maquinaService) {
        this.maquinaService = maquinaService;
    }

    @GetMapping
    @Operation(summary = "Lista todas las maquinas")
    public List<Maquina> listar(){
        return maquinaService.listarTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca una maquina por id")
    public ResponseEntity<Maquina> buscarPorId(@PathVariable Integer id){
        return maquinaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Lista maquinas por estado")
    public List<Maquina> buscarPorEstado(@PathVariable String estado){
        return maquinaService.buscarPorEstado(estado);
    }

    @PostMapping
    @Operation(summary = "Guarda una maquina")
    public ResponseEntity<Maquina> guardar(@RequestBody Maquina maquina){
        Maquina nueva = maquinaService.guardar(maquina);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una maquina por id")
    public ResponseEntity<Maquina> actualizar(@PathVariable Integer id, @RequestBody Maquina maquina){
        try {
            Maquina actualizada = maquinaService.actualizarMaquina(id, maquina);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una maquina por id")
    public ResponseEntity<Maquina> eliminar(@PathVariable Integer id){
        maquinaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
