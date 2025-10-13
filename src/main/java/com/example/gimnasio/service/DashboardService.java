package com.example.gimnasio.service;

import com.example.gimnasio.dto.SedeConcurrenciaDTO;
import com.example.gimnasio.dto.SedeGananciaDTO;

import java.util.List;

public interface DashboardService {
    List<SedeGananciaDTO> obtenerSedesConMasGanancias();
    List<SedeConcurrenciaDTO> obtenerSedesMasConcurridas();

    Double obtenerIngresosTotales();
    Long obtenerTotalClientes();
    Long obtenerTotalProveedores();
    Long obtenerTotalMaquinas();
}
