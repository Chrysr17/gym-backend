package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Empleado;
import com.example.gimnasio.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public List<Empleado> listar(){
        return empleadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarPorId(@PathVariable Integer id) {
        return empleadoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Empleado guardar(@RequestBody Empleado empleado){
        return empleadoService.guardar(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(@PathVariable Integer id, @RequestBody Empleado empleado){
        return empleadoService.buscarPorId(id)
                .map(existente ->{
                    existente.setNombre(empleado.getNombre());
                    existente.setDni(empleado.getDni());
                    existente.setTelefono(empleado.getTelefono());
                    existente.setSede(empleado.getSede());
                    existente.setCargo(empleado.getCargo());
                    return ResponseEntity.ok(empleadoService.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> eliminar(@PathVariable Integer id){
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
