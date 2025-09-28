package com.example.gimnasio.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pago_id")
    private Integer pagoId;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference("cliente-pagos")
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPago estado = EstadoPago.Pendiente;

}
