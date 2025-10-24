package com.example.gimnasio.service;

import com.example.gimnasio.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> listarTodos();
    Optional<Empleado> buscarPorId(Integer id);
    Empleado guardar(Empleado empleado);
    Empleado actualizarEmpleado(Integer id, Empleado empleadoActualizado);
    void eliminar(Integer id);
    List<Empleado> listarPorSede(Integer sedeId);
    List<Empleado> listarPorCargo(String cargo);
    List<Empleado> buscarPorNombre(String nombre);
    Optional<Empleado> buscarPorDni(String dni);
}
