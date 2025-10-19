package com.example.gimnasio.repository;

import com.example.gimnasio.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository <Proveedor, Integer>{
    List<Proveedor> findByCategoria(String categoria);
    List<Proveedor> findByNombreContainingIgnoreCaseAndCategoriaContainingIgnoreCase(String nombre, String categoria);
}
