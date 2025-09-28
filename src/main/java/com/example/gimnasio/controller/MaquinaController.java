package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Maquina;
import com.example.gimnasio.service.MaquinaService;
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
    public List<Maquina> listar(){
        return maquinaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maquina> buscarPorId(@PathVariable Integer id){
        return maquinaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Maquina guardar(@RequestBody Maquina maquina){
        return maquinaService.guardar(maquina);
    }



}
