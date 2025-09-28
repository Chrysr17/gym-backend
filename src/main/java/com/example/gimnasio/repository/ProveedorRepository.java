package com.example.gimnasio.repository;

import com.example.gimnasio.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository <Proveedor, Integer>{
}
