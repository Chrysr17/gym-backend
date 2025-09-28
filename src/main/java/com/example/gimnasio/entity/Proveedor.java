package com.example.gimnasio.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proveedor_id")
    private Integer proveedorId;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String categoria;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("proveedor-maquinas")
    private List<Maquina> maquinas;

}
