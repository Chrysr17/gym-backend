package com.example.gimnasio.service.impl;

import com.example.gimnasio.entity.Maquina;
import com.example.gimnasio.repository.MaquinaRepository;
import com.example.gimnasio.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinaServiceImpl implements MaquinaService {

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Override
    public List<Maquina> listarTodas() {
        return List.of();
    }

    @Override
    public Optional<Maquina> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public Maquina guardar(Maquina maquina) {
        return null;
    }

    @Override
    public Maquina actualizarMaquina(Integer id, Maquina maquina) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }
}
