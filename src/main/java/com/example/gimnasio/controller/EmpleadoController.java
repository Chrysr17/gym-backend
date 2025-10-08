package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Empleado;
import com.example.gimnasio.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

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

    @GetMapping("/sede/{sedeId}")
    public List<Empleado> listarPorSede(@PathVariable Integer sedeId){
        return empleadoService.listarPorSede(sedeId);
    }

    @GetMapping("/cargo/{cargo}")
    public List<Empleado> listarPorCargo(@PathVariable String cargo){
        return empleadoService.listarPorCargo(cargo);
    }

    @PostMapping
    public ResponseEntity<Empleado> guardar(@RequestBody Empleado empleado){
        Empleado nuevo = empleadoService.guardar(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(@PathVariable Integer id, @RequestBody Empleado empleado){
        try {
            Empleado actualizado = empleadoService.actualizarEmpleado(id, empleado);
            return ResponseEntity.ok(actualizado);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> eliminar(@PathVariable Integer id){
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
