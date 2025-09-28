package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.entity.Pago;
import com.example.gimnasio.service.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
