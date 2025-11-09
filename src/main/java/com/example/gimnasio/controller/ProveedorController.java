package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Maquina;
import com.example.gimnasio.entity.Proveedor;
import com.example.gimnasio.service.MaquinaService;
import com.example.gimnasio.service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;
    private final MaquinaService maquinaService;

    public ProveedorController(ProveedorService proveedorService, MaquinaService maquinaService) {
        this.proveedorService = proveedorService;
        this.maquinaService = maquinaService;
    }

    @GetMapping
    @Operation(summary = "Lista todos los proveedores")
    public ResponseEntity<List<Proveedor>> listar(){
        List<Proveedor> proveedores = proveedorService.listarTodos();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca un proveedor por id")
    public ResponseEntity<Proveedor> buscarPorId(@PathVariable Integer id){
        return proveedorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Lista proveedores por categoria")
    public ResponseEntity<List<Proveedor>> listarPorCategoria(@PathVariable String categoria){
        List<Proveedor> proveedores = proveedorService.listarPorCategoria(categoria);
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar proveedores por nombre y categoria")
    public ResponseEntity<List<Proveedor>> buscarProveedores(
            @RequestParam(required = false)String nombre,
            @RequestParam(required = false)String categoria) {
        List<Proveedor> proveedores = proveedorService.buscarPorNombreYCategoria(nombre, categoria);
        return ResponseEntity.ok(proveedores);
    }
    @GetMapping("/{id}/maquinas")
    @Operation(summary = "Lista maquinas por proveedor")
    public ResponseEntity<List<Maquina>> listarPorProveedor(@PathVariable Integer id){
        List<Maquina> maquinas = maquinaService.listarPorProvedor(id);
        return ResponseEntity.ok(maquinas);
    }

    @PostMapping
    @Operation(summary = "Guarda un proveedor")
    public ResponseEntity<Proveedor> guardar(@RequestBody Proveedor proveedor){
        Proveedor nuevo = proveedorService.guardar(proveedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un proveedor por id")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Integer id, @RequestBody Proveedor proveedor){
        try {
            Proveedor actualizado =  proveedorService.actualizar(id, proveedor);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un proveedor por id")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
