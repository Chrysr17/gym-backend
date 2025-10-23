package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Empleado;
import com.example.gimnasio.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Lista todos los empleados")
    public ResponseEntity<List<Empleado>> listar(){
        List<Empleado> empleados = empleadoService.listarTodos();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca un empleado por id")
    public ResponseEntity<Empleado> buscarPorId(@PathVariable Integer id) {
        return empleadoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sede/{sedeId}")
    @Operation(summary = "Lista empleados por sedeId")
    public ResponseEntity<List<Empleado>> listarPorSede(@PathVariable Integer sedeId){
        List<Empleado> empleados = empleadoService.listarPorSede(sedeId);
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/cargo/{cargo}")
    @Operation(summary = "Lista Empleados por cargo")
    public ResponseEntity<List<Empleado>> listarPorCargo(@PathVariable String cargo){
        List<Empleado> empleados = empleadoService.listarPorCargo(cargo);
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Busca empleados por nombres y apellidos")
    public ResponseEntity<List<Empleado>> buscar(@RequestParam String nombre){
        List<Empleado> empleados = empleadoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(empleados);
    }

    @PostMapping
    @Operation(summary = "Guarda un nuevo empleado")
    public ResponseEntity<Empleado> guardar(@RequestBody Empleado empleado){
        Empleado nuevo = empleadoService.guardar(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un empleado por id")
    public ResponseEntity<Empleado> actualizar(@PathVariable Integer id, @RequestBody Empleado empleado){
        try {
            Empleado actualizado = empleadoService.actualizarEmpleado(id, empleado);
            return ResponseEntity.ok(actualizado);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un empleado por id")
    public ResponseEntity<Empleado> eliminar(@PathVariable Integer id){
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
