package com.example.gimnasio.controller;

import com.example.gimnasio.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/resumen")
    public Map<String, Object> obtenerResumen(){
        Map<String, Object> resumen = new HashMap<>();
        resumen.put("ingresosTotales", dashboardService.obtenerIngresosTotales());
        resumen.put("clientesTotales", dashboardService.obtenerTotalClientes());
        resumen.put("proveedoresTotales", dashboardService.obtenerTotalProveedores());
        resumen.put("maquinasTotales", dashboardService.obtenerTotalMaquinas());
        resumen.put("sedesConMasGanancias", dashboardService.obtenerSedesConMasGanancias());
        resumen.put("sedesMasConcurridas", dashboardService.obtenerSedesMasConcurridas());
        return resumen;
    }

}
