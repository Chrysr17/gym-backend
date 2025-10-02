package com.example.gimnasio.repository;

import com.example.gimnasio.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {

    List<Pago> findByClienteClienteId(Integer clienteId);

    @Query("SELECT SUM(p.monto) FROM Pago p WHERE p.estado = 'Pagado'")
    Optional <Double> sumPagosCompletados();

    @Query("SELECT s.nombre, SUM(p.monto) as totalGanancias " +
            "FROM Pago p " +
            "JOIN p.cliente c " +
            "JOIN c.sede s " +
            "GROUP BY s.sedeId " +
            "ORDER BY totalGanancias DESC")
    List<Object[]> sedeConMasGanancias();

}
