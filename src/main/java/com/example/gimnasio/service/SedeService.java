package com.example.gimnasio.service;

import com.example.gimnasio.entity.Sede;

import java.util.List;
import java.util.Optional;

public interface SedeService {
    List<Sede> listarTodas();
    Optional<Sede> buscarPorId(Integer id);
    Sede guardar(Sede sede);
    Sede actualizarSede(Integer id, Sede sedeActualizada);
    void eliminar(Integer id);
}
