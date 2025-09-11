package com.example.gimnasio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "maquina")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maquina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maquina_id" )
    private Integer maquinaId;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descrpcion;

    @Column(length = 255)
    private String imagen;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.Operativa;

    @ManyToOne
    @JoinColumn(name = "sede_id", nullable = false)
    @JsonBackReference
    private Sede sede;

    public enum Estado {
        Operativa, Mantenimiento, Dañada
    }
}
