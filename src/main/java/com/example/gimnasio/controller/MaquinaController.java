package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Maquina;
import com.example.gimnasio.service.MaquinaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
