package com.example.gimnasio.dto;

public class SedeConcurrenciaDto {
    private String nombre;
    private Long totalClientes;

    public SedeConcurrenciaDto(String nombre, Long totalClientes) {
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
