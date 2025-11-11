package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.entity.EstadoPago;
import com.example.gimnasio.entity.Pago;
import com.example.gimnasio.service.PagoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagoService pagoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pago getPago() {
        Cliente c = new Cliente();
        c.setClienteId(1);
        c.setNombre("Juan Pérez");

        Pago p = new Pago();
        p.setPagoId(1);
        p.setCliente(c);
        p.setFecha(LocalDate.now());
        p.setMonto(BigDecimal.valueOf(120.50));
        p.setEstado(EstadoPago.Pagado);
        return p;
    }

    @Test
    void testListarPagos() throws Exception {
        Mockito.when(pagoService.listarTodos()).thenReturn(List.of(getPago()));

        mockMvc.perform(get("/api/pagos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].monto").value(120.50));
    }

    @Test
    void testBuscarPorId_Encontrado() throws Exception {
        Mockito.when(pagoService.buscarPorId(1)).thenReturn(Optional.of(getPago()));

        mockMvc.perform(get("/api/pagos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Pagado"));
    }

    @Test
    void testBuscarPorId_NoEncontrado() throws Exception {
        Mockito.when(pagoService.buscarPorId(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/pagos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testListarPorCliente() throws Exception {
        Mockito.when(pagoService.listarPorCliente(1)).thenReturn(List.of(getPago()));

        mockMvc.perform(get("/api/pagos/cliente/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cliente.nombre").value("Juan Pérez"));
    }

    @Test
    void testBuscarPorRangoFechas() throws Exception {
        LocalDate inicio = LocalDate.now().minusDays(1);
        LocalDate fin = LocalDate.now().plusDays(1);

        Mockito.when(pagoService.buscarPorRangoFecha(inicio, fin)).thenReturn(List.of(getPago()));

        mockMvc.perform(get("/api/pagos/rango-fechas")
                        .param("inicio", inicio.toString())
                        .param("fin", fin.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("Pagado"));
    }

    @Test
    void testActualizarPago_Exitoso() throws Exception {
        Mockito.when(pagoService.actualizarPago(Mockito.eq(1), any(Pago.class))).thenReturn(getPago());

        mockMvc.perform(put("/api/pagos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getPago())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Pagado"));
    }

    @Test
    void testRegistrarPago() throws Exception {
        Mockito.when(pagoService.registrarPago(Mockito.eq(1), any(Pago.class))).thenReturn(getPago());

        mockMvc.perform(post("/api/pagos/registrar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getPago())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.monto").value(120.50));
    }

    @Test
    void testEliminarPago() throws Exception {
        mockMvc.perform(delete("/api/pagos/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(pagoService, Mockito.times(1)).eliminar(1);
    }

}