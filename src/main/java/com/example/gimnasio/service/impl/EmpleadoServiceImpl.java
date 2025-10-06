package com.example.gimnasio.service.impl;

import com.example.gimnasio.entity.Empleado;
import com.example.gimnasio.repository.EmpleadoRepository;
import com.example.gimnasio.service.EmpleadoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> buscarPorId(Integer id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado actualizarEmpleado(Integer id, Empleado empleadoActualizado) {
        return empleadoRepository.findById(id)
                .map(empleadoExistente -> {
                    empleadoExistente.setNombre(empleadoActualizado.getNombre());
                    empleadoExistente.setTelefono(empleadoActualizado.getTelefono());
                    empleadoExistente.setCargo(empleadoActualizado.getCargo());
                    empleadoExistente.setSede(empleadoActualizado.getSede());
                    return empleadoRepository.save(empleadoActualizado);
                }).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    }

    @Override
    public void eliminar(Integer id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public List<Empleado> listarPorSede(Integer sedeId) {
        return empleadoRepository.findBySede_SedeId(sedeId);
    }
}
