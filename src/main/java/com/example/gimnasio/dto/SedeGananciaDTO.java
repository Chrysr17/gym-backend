package com.example.gimnasio.dto;

public class SedeGananciaDTO {
    private String nombre;
    private Double ganancias;

    public SedeGananciaDTO() {
    }

    public SedeGananciaDTO(String nombre, Double ganancias) {
        this.nombre = nombre;
        this.ganancias = ganancias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getGanancias() {
        return ganancias;
    }

    public void setGanancias(Double ganancias) {
        this.ganancias = ganancias;
    }
}
