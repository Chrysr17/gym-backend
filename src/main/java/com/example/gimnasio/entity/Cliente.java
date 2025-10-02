package com.example.gimnasio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Integer clienteId;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 20)
    private String dni;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String correo;

    @Column(length = 200)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "sede_id", nullable = false)
    @JsonIgnoreProperties("clientes")
    private Sede sede;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(name = "mensualidad", precision = 10, scale = 2)
    private BigDecimal mensualidad;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("cliente-pagos")
    private List<Pago> pagos;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    @JsonBackReference("cliente-usuario")
    private Usuario usuario;

}
