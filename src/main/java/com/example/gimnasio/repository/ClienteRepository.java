package com.example.gimnasio.repository;

import com.example.gimnasio.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByDni(String dni);

    @Query("SELECT s.nombre, COUNT(c.clienteId) as totalClientes " +
            "FROM Cliente c " +
            "JOIN c.sede s " +
            "GROUP BY s.sedeId " +
            "ORDER BY totalClientes DESC")
    List<Object[]> sedeMasConcurrida();

}
