package com.example.gimnasio.service.impl;

import com.example.gimnasio.repository.ClienteRepository;
import com.example.gimnasio.repository.MaquinaRepository;
import com.example.gimnasio.repository.PagoRepository;
import com.example.gimnasio.repository.ProveedorRepository;
import com.example.gimnasio.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashBoardServiceImpl implements DashboardService {

    private final PagoRepository pagoRepository;
    private final ClienteRepository clienteRepository;
    private final ProveedorRepository proveedorRepository;
    private final MaquinaRepository maquinaRepository;

    public DashBoardServiceImpl(PagoRepository pagoRepository, ClienteRepository clienteRepository, ProveedorRepository proveedorRepository, MaquinaRepository maquinaRepository) {
        this.pagoRepository = pagoRepository;
        this.clienteRepository = clienteRepository;
        this.proveedorRepository = proveedorRepository;
        this.maquinaRepository = maquinaRepository;
    }

    @Override
    public List<Object[]> obtenerSedesConMasGanancias() {
        return pagoRepository.sedeConMasGanancias();
    }

    @Override
    public List<Object[]> obtenerSedesMasConcurridas() {
        return clienteRepository.sedeMasConcurrida();
    }

    @Override
    public Double obtenerIngresosTotales() {
        return pagoRepository.sumPagosCompletados().orElse(0.0);
    }

    @Override
    public Long obtenerTotalClientes() {
        return clienteRepository.count();
    }

    @Override
    public Long obtenerTotalProveedores() {
        return proveedorRepository.count();
    }

    @Override
    public Long obtenerTotalMaquinas() {
        return maquinaRepository.count();
    }
}
