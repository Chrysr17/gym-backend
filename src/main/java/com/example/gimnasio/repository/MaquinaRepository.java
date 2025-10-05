package com.example.gimnasio.repository;

import com.example.gimnasio.entity.EstadoMaquina;
import com.example.gimnasio.entity.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Integer> {

    List<Maquina> findByEstado(EstadoMaquina estado);

}
