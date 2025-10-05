package com.example.gimnasio.service;

import com.example.gimnasio.entity.Maquina;

import java.util.List;
import java.util.Optional;

public interface MaquinaService {
    List<Maquina> listarTodas();
    Optional<Maquina> buscarPorId(Integer id);
    Maquina guardar(Maquina maquina);
    Maquina actualizarMaquina (Integer id, Maquina maquinaActualizada);
    void eliminar(Integer id);
    List<Maquina> buscarPorEstado(String estado);
}
