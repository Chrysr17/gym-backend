package com.example.gimnasio.repository;

import com.example.gimnasio.entity.Cargo;
import com.example.gimnasio.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    List<Empleado> findBySede_SedeId(Integer sedeId);
    List<Empleado> findByCargoContainingIgnoreCase(Cargo cargo);
}
