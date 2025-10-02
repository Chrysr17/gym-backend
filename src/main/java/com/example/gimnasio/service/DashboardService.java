package com.example.gimnasio.service;

import java.util.List;

public interface DashboardService {
    List<Object[]> obtenerSedesConMasGanancias();
    List<Object[]> obtenerSedesMasConcurridas();

    Double obtenerIngresosTotales();
    Long obtenerTotalClientes();
    Long obtenerTotalProveedores();
    Long obtenerTotalMaquinas();
}
