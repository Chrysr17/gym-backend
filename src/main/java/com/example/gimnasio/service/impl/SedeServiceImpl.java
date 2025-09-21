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

    @Autowired
    private SedeRepository sedeRepository;

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
    public void eliminar(Integer id) {
        sedeRepository.deleteById(id);
    }
}
