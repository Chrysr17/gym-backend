package com.example.gimnasio.service.impl;

import com.example.gimnasio.dto.SedeConcurrenciaDto;
import com.example.gimnasio.dto.SedeGananciaDto;
import com.example.gimnasio.repository.ClienteRepository;
import com.example.gimnasio.repository.MaquinaRepository;
import com.example.gimnasio.repository.PagoRepository;
import com.example.gimnasio.repository.ProveedorRepository;
import com.example.gimnasio.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<SedeGananciaDto> obtenerSedesConMasGanancias() {
        return pagoRepository.sedeConMasGanancias()
                .stream()
                .map(obj -> new SedeGananciaDto((String) obj[0], ((Number) obj[1]).doubleValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SedeConcurrenciaDto> obtenerSedesMasConcurridas() {
        return clienteRepository.sedeMasConcurrida()
                .stream()
                .map(obj -> new SedeConcurrenciaDto((String) obj[0],((Number) obj[1]).longValue()))
                .collect(Collectors.toList());
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
