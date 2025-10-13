package com.example.gimnasio.dto;

public class SedeConcurrenciaDTO {
    private String nombre;
    private Long totalClientes;

    public SedeConcurrenciaDTO(String nombre, Long totalClientes) {
        this.nombre = nombre;
        this.totalClientes = totalClientes;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getTotalClientes() {
        return totalClientes;
    }

}
