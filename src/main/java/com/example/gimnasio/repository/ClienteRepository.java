package com.example.gimnasio.repository;

import com.example.gimnasio.entity.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByDni(String dni);
    List<Cliente> findBySede_SedeId(Integer sedeId);

    @Query("SELECT s.nombre, COUNT(c.clienteId) as totalClientes " +
            "FROM Cliente c " +
            "JOIN c.sede s " +
            "GROUP BY s.sedeId " +
            "ORDER BY totalClientes DESC")
    List<Object[]> sedeMasConcurrida();

    @Query("SELECT c FROM Cliente c")
    List<Cliente> listarIncluyendoEliminados();

    @Modifying
    @Transactional
    @Query(value = "UPDATE cliente SET eliminado = false WHERE cliente_id = :id", nativeQuery = true)
    void restaurarPorId(@Param("id") Integer id);

}
