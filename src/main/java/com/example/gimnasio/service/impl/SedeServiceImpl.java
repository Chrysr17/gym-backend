package com.example.gimnasio.service.impl;

import com.example.gimnasio.entity.Sede;
import com.example.gimnasio.repository.SedeRepository;
import com.example.gimnasio.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SedeServiceImpl implements SedeService {

    private final SedeRepository sedeRepository;

    public SedeServiceImpl(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    @Override
    public List<Sede> listarTodas() {
        return sedeRepository.findAll();
    }

    @Override
    public Optional<Sede> buscarPorId(Integer id) {
        return sedeRepository.findById(id);
    }

    @Override
    public Sede guardar(Sede sede) {
        return sedeRepository.save(sede);
    }

    @Override
    public Sede actualizarSede(Integer id, Sede sedeActualizada) {
        return sedeRepository.findById(id)
                .map(sedeExistente -> {
                    sedeExistente.setNombre(sedeActualizada.getNombre());
                    sedeExistente.setDireccion(sedeActualizada.getDireccion());
                    sedeExistente.setMaquinas(sedeActualizada.getMaquinas());
                    sedeExistente.setEmpleados(sedeActualizada.getEmpleados());
                    return sedeRepository.save(sedeActualizada);
                }).orElseThrow(() -> new RuntimeException("Sede no encontrada"));
    }

    @Override
    public void eliminar(Integer id) {
        sedeRepository.deleteById(id);
    }
}
