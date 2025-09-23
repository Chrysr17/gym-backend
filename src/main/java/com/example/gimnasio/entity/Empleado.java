package com.example.gimnasio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleado_id")
    private Integer empleadoId;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, unique = false, length = 20)
    private String dni;

    @Column(length = 200)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JsonBackReference
    private Cargo cargo;

    @ManyToOne
    @JoinColumn(name = "sede_id", nullable = false)
    @JsonBackReference("sede-empleados")
    private Sede sede;

    public enum Cargo {
        Recepcionista, Entrenador, Limpieza
    }

}
