package com.example.gimnasio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.gimnasio.entity.EstadoMaquina;
import com.example.gimnasio.entity.Maquina;
import com.example.gimnasio.entity.Proveedor;
import com.example.gimnasio.repository.MaquinaRepository;
import com.example.gimnasio.service.impl.MaquinaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

class MaquinaServiceTest {

    @Mock
    private MaquinaRepository maquinaRepository;

    @InjectMocks
    private MaquinaServiceImpl maquinaService;

    private Maquina maquina;
    private Proveedor proveedor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        proveedor = new Proveedor();
        proveedor.setProveedorId(1);
        proveedor.setNombre("Proveedores Gym Perú");

        maquina = new Maquina();
        maquina.setMaquinaId(1);
        maquina.setNombre("Press de banca");
        maquina.setDescripcion("Maquina para pecho");
        maquina.setEstado(EstadoMaquina.Operativa);
        maquina.setProveedor(proveedor);
    }

    @Test
    void testListarTodas() {
        when(maquinaRepository.findAll()).thenReturn(List.of(maquina));

        List<Maquina> result = maquinaService.listarTodas();

        assertEquals(1, result.size());
        verify(maquinaRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Encontrado() {
        when(maquinaRepository.findById(1)).thenReturn(Optional.of(maquina));

        Optional<Maquina> result = maquinaService.buscarPorId(1);

        assertTrue(result.isPresent());
        assertEquals("Press de banca", result.get().getNombre());
    }

    @Test
    void testBuscarPorId_NoEncontrado() {
        when(maquinaRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Maquina> result = maquinaService.buscarPorId(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGuardar() {
        when(maquinaRepository.save(any(Maquina.class))).thenReturn(maquina);

        Maquina result = maquinaService.guardar(maquina);

        assertNotNull(result);
        assertEquals("Press de banca", result.getNombre());
        verify(maquinaRepository, times(1)).save(maquina);
    }

    @Test
    void testActualizarMaquina_Exitoso() {
        Maquina actualizada = new Maquina();
        actualizada.setNombre("Press Inclinado");
        actualizada.setDescripcion("Actualizada");
        actualizada.setEstado(EstadoMaquina.Mantenimiento);

        when(maquinaRepository.findById(1)).thenReturn(Optional.of(maquina));
        when(maquinaRepository.save(any(Maquina.class))).thenReturn(maquina);

        Maquina result = maquinaService.actualizarMaquina(1, actualizada);

        assertEquals(EstadoMaquina.Mantenimiento, result.getEstado());
        verify(maquinaRepository, times(1)).save(any(Maquina.class));
    }

    @Test
    void testActualizarMaquina_NoEncontrada() {
        when(maquinaRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> maquinaService.actualizarMaquina(99, maquina));
    }

    @Test
    void testEliminar() {
        doNothing().when(maquinaRepository).deleteById(1);

        maquinaService.eliminar(1);

        verify(maquinaRepository, times(1)).deleteById(1);
    }

    @Test
    void testBuscarPorEstado() {
        when(maquinaRepository.findByEstado(EstadoMaquina.Operativa)).thenReturn(List.of(maquina));

        List<Maquina> result = maquinaService.buscarPorEstado("Operativa");

        assertEquals(1, result.size());
        assertEquals(EstadoMaquina.Operativa, result.get(0).getEstado());
    }

    @Test
    void testBuscarPorNombre() {
        when(maquinaRepository.findByNombreContainingIgnoreCase("press")).thenReturn(List.of(maquina));

        List<Maquina> result = maquinaService.buscarPorNombre("press");

        assertEquals(1, result.size());
        verify(maquinaRepository).findByNombreContainingIgnoreCase("press");
    }

    @Test
    void testListarPorProveedor() {
        when(maquinaRepository.findByProveedor_ProveedorId(1)).thenReturn(List.of(maquina));

        List<Maquina> result = maquinaService.listarPorProvedor(1);

        assertEquals(1, result.size());
        assertEquals("Proveedores Gym Perú", result.get(0).getProveedor().getNombre());
    }
}