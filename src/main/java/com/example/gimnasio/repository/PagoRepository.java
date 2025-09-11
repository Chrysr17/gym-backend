package com.example.gimnasio.repository;

import com.example.gimnasio.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    List<Pago> findByClienteClienteId(Integer clienteId);
}
