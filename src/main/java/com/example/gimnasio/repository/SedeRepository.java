package com.example.gimnasio.repository;

import com.example.gimnasio.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
    List<Sede> findByNombreContainingIgnoreCase(String nombre);
}
