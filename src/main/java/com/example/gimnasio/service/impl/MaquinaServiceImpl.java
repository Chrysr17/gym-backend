package com.example.gimnasio.service.impl;

import com.example.gimnasio.entity.EstadoMaquina;
import com.example.gimnasio.entity.Maquina;
import com.example.gimnasio.repository.MaquinaRepository;
import com.example.gimnasio.service.MaquinaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinaServiceImpl implements MaquinaService {

    private final MaquinaRepository maquinaRepository;

    public MaquinaServiceImpl(MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    @Override
    public List<Maquina> listarTodas() {
        return maquinaRepository.findAll();
    }

    @Override
    public Optional<Maquina> buscarPorId(Integer id) {
        return maquinaRepository.findById(id);

    }

    @Override
    public Maquina guardar(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }

    @Override
    public Maquina actualizarMaquina(Integer id, Maquina maquinaActualizada) {
        return maquinaRepository.findById(id)
                .map(maquinaExistente -> {
                    maquinaExistente.setNombre(maquinaActualizada.getNombre());
                    maquinaExistente.setDescripcion(maquinaActualizada.getDescripcion());
                    maquinaExistente.setImagen(maquinaActualizada.getImagen());
                    maquinaExistente.setEstado(maquinaActualizada.getEstado());
                    if (maquinaActualizada.getSede() != null) {
                        maquinaExistente.setSede(maquinaActualizada.getSede());
                    }
                    if (maquinaActualizada.getProveedor() != null) {
                        maquinaExistente.setProveedor(maquinaActualizada.getProveedor());
                    }
                    return maquinaRepository.save(maquinaActualizada);
                }).orElseThrow(() -> new RuntimeException("Maquina no encontrada"));
    }

    @Override
    public void eliminar(Integer id) {
        maquinaRepository.deleteById(id);
    }

    @Override
    public List<Maquina> buscarPorEstado(String estado) {
        EstadoMaquina estadoMaquina = EstadoMaquina.valueOf(estado);
        return maquinaRepository.findByEstado(estadoMaquina);
    }

    @Override
    public List<Maquina> buscarPornombre(String nomnbre) {
        return List.of();
    }

}
