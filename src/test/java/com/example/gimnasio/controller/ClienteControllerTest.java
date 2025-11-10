package com.example.gimnasio.controller;

import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente getCliente() {
        Cliente c = new Cliente();
        c.setClienteId(1);
        c.setNombre("Juan Pérez");
        c.setDni("12345678");
        c.setTelefono("987654321");
        c.setCorreo("juan@example.com");
        c.setDireccion("Av. Lima 123");
        c.setFechaPago(LocalDate.now());
        c.setMensualidad(BigDecimal.valueOf(150.00));
        return c;
    }

    @Test
    void testListarClientes() throws Exception {
        Mockito.when(clienteService.listarTodos()).thenReturn(List.of(getCliente()));

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan Pérez"));
    }

    @Test
    void testBuscarPorId_Encontrado() throws Exception {
        Mockito.when(clienteService.buscarPorId(1)).thenReturn(Optional.of(getCliente()));

        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value("12345678"));
    }

    @Test
    void testBuscarPorId_NoEncontrado() throws Exception {
        Mockito.when(clienteService.buscarPorId(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGuardarCliente() throws Exception {
        Cliente cliente = getCliente();
        Mockito.when(clienteService.guardar(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"));
    }

    @Test
    void testEliminarCliente() throws Exception {
        mockMvc.perform(delete("/api/clientes/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(clienteService, Mockito.times(1)).eliminarLogico(1);
    }

    @Test
    void testActualizarCliente_Exitoso() throws Exception {
        Cliente cliente = getCliente();
        Mockito.when(clienteService.actualizarCliente(Mockito.eq(1), any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(put("/api/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"));
    }

    @Test
    void testBuscarPorNombre() throws Exception {
        Mockito.when(clienteService.buscarPorNombre("Juan")).thenReturn(List.of(getCliente()));

        mockMvc.perform(get("/api/clientes/buscar")
                        .param("nombre", "Juan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan Pérez"));
    }
}