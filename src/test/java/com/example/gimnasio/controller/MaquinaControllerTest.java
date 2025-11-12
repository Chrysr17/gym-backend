package com.example.gimnasio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.gimnasio.entity.EstadoMaquina;
import com.example.gimnasio.entity.Maquina;
import com.example.gimnasio.entity.Proveedor;
import com.example.gimnasio.service.MaquinaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MaquinaController.class)
class MaquinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaquinaService maquinaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Maquina getMaquina() {
        Proveedor p = new Proveedor();
        p.setProveedorId(1);
        p.setNombre("Gym Proveedor");

        Maquina m = new Maquina();
        m.setMaquinaId(1);
        m.setNombre("Press de banca");
        m.setDescripcion("Maquina de pecho");
        m.setEstado(EstadoMaquina.Operativa);
        m.setProveedor(p);
        return m;
    }

    @Test
    void testListarMaquinas() throws Exception {
        Mockito.when(maquinaService.listarTodas()).thenReturn(List.of(getMaquina()));

        mockMvc.perform(get("/api/maquinas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Press de banca"));
    }

    @Test
    void testBuscarPorId_Encontrado() throws Exception {
        Mockito.when(maquinaService.buscarPorId(1)).thenReturn(Optional.of(getMaquina()));

        mockMvc.perform(get("/api/maquinas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Operativa"));
    }

    @Test
    void testBuscarPorId_NoEncontrado() throws Exception {
        Mockito.when(maquinaService.buscarPorId(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/maquinas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testBuscarPorEstado() throws Exception {
        Mockito.when(maquinaService.buscarPorEstado("Operativa")).thenReturn(List.of(getMaquina()));

        mockMvc.perform(get("/api/maquinas/estado/Operativa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("Operativa"));
    }

    @Test
    void testBuscarPorNombre() throws Exception {
        Mockito.when(maquinaService.buscarPorNombre("press")).thenReturn(List.of(getMaquina()));

        mockMvc.perform(get("/api/maquinas/buscar")
                        .param("nombre", "press"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Press de banca"));
    }

    @Test
    void testGuardarMaquina() throws Exception {
        Mockito.when(maquinaService.guardar(any(Maquina.class))).thenReturn(getMaquina());

        mockMvc.perform(post("/api/maquinas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getMaquina())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Press de banca"));
    }

    @Test
    void testActualizarMaquina_Exitoso() throws Exception {
        Mockito.when(maquinaService.actualizarMaquina(Mockito.eq(1), any(Maquina.class))).thenReturn(getMaquina());

        mockMvc.perform(put("/api/maquinas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getMaquina())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Operativa"));
    }

    @Test
    void testEliminarMaquina() throws Exception {
        mockMvc.perform(delete("/api/maquinas/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(maquinaService, Mockito.times(1)).eliminar(1);
    }
}