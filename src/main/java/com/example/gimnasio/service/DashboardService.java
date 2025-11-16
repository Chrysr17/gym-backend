package com.example.gimnasio.service;

import com.example.gimnasio.dto.SedeConcurrenciaDto;
import com.example.gimnasio.dto.SedeGananciaDto;

import java.util.List;

public interface DashboardService {
    List<SedeGananciaDto> obtenerSedesConMasGanancias();
    List<SedeConcurrenciaDto> obtenerSedesMasConcurridas();

    Double obtenerIngresosTotales();
    Long obtenerTotalClientes();
    Long obtenerTotalProveedores();
    Long obtenerTotalMaquinas();
}
