package com.example.gimnasio.service.impl;

import com.example.gimnasio.entity.Proveedor;
import com.example.gimnasio.repository.ProveedorRepository;
import com.example.gimnasio.service.ProveedorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> buscarPorId(Integer id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor actualizarProveedor(Integer id, Proveedor proveedorActualizado) {
        return proveedorRepository.findById(id)
                .map(proveedorExistente -> {
                    proveedorExistente.setNombre(proveedorActualizado.getNombre());
                    proveedorExistente.setCategoria(proveedorActualizado.getCategoria());
                    return proveedorRepository.save(proveedorExistente);
                }).orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    @Override
    public void eliminar(Integer id) {
        proveedorRepository.deleteById(id);
    }

    @Override
    public List<Proveedor> listarPorCategoria(String categoria) {
        return proveedorRepository.findByCategoriaContainingIgnoreCase(categoria);
    }

    @Override
    public List<Proveedor> buscarPorNombreYCategoria(String nombre, String categoria) {
        if (nombre != null && categoria != null){
            return proveedorRepository.findByNombreContainingIgnoreCaseAndCategoriaContainingIgnoreCase(nombre, categoria);
        } else if (nombre != null) {
            return proveedorRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (categoria != null) {
            return proveedorRepository.findByCategoriaContainingIgnoreCase(categoria);
        }else {
            return proveedorRepository.findAll();
        }
    }
}
