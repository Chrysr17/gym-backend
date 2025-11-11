package com.example.gimnasio.service;

import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.entity.EstadoPago;
import com.example.gimnasio.entity.Pago;
import com.example.gimnasio.repository.ClienteRepository;
import com.example.gimnasio.repository.PagoRepository;
import com.example.gimnasio.service.impl.PagoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class PagoServiceTest {
    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private PagoServiceImpl pagoService;

    private Pago pago;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setClienteId(1);
        cliente.setNombre("Juan Pérez");

        pago = new Pago();
        pago.setPagoId(1);
        pago.setCliente(cliente);
        pago.setFecha(LocalDate.now());
        pago.setMonto(BigDecimal.valueOf(120.50));
        pago.setEstado(EstadoPago.Pagado);
    }

    @Test
    void testListarTodos() {
        when(pagoRepository.findAll()).thenReturn(List.of(pago));

        List<Pago> result = pagoService.listarTodos();

        assertEquals(1, result.size());
        verify(pagoRepository, times(1)).findAll();
    }

    @Test
    void testListarPorCliente() {
        when(pagoRepository.findByClienteClienteId(1)).thenReturn(List.of(pago));

        List<Pago> result = pagoService.listarPorCliente(1);

        assertEquals(1, result.size());
        assertEquals("Juan Pérez", result.get(0).getCliente().getNombre());
    }

    @Test
    void testBuscarPorRangoFecha() {
        LocalDate inicio = LocalDate.now().minusDays(1);
        LocalDate fin = LocalDate.now().plusDays(1);

        when(pagoRepository.findByFechaBetween(inicio, fin)).thenReturn(List.of(pago));

        List<Pago> result = pagoService.buscarPorRangoFecha(inicio, fin);

        assertEquals(1, result.size());
        verify(pagoRepository, times(1)).findByFechaBetween(inicio, fin);
    }

    @Test
    void testBuscarPorId_Encontrado() {
        when(pagoRepository.findById(1)).thenReturn(Optional.of(pago));

        Optional<Pago> result = pagoService.buscarPorId(1);

        assertTrue(result.isPresent());
        assertEquals(BigDecimal.valueOf(120.50), result.get().getMonto());
    }

    @Test
    void testGuardar() {
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        Pago result = pagoService.guardar(pago);

        assertNotNull(result);
        verify(pagoRepository, times(1)).save(pago);
    }

    @Test
    void testActualizarPago_Exitoso() {
        Pago pagoActualizado = new Pago();
        pagoActualizado.setMonto(BigDecimal.valueOf(150.00));
        pagoActualizado.setEstado(EstadoPago.Pendiente);
        pagoActualizado.setFecha(LocalDate.now().plusDays(1));

        when(pagoRepository.findById(1)).thenReturn(Optional.of(pago));
        when(pagoRepository.save(any(Pago.class))).thenReturn(pagoActualizado);

        Pago result = pagoService.actualizarPago(1, pagoActualizado);

        assertEquals(BigDecimal.valueOf(150.00), result.getMonto());
        verify(pagoRepository, times(1)).save(any(Pago.class));
    }

    @Test
    void testActualizarPago_NoEncontrado() {
        when(pagoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> pagoService.actualizarPago(99, pago));
    }

    @Test
    void testRegistrarPago_Exitoso() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        Pago result = pagoService.registrarPago(1, pago);

        assertEquals(EstadoPago.Pagado, result.getEstado());
        assertEquals(cliente, result.getCliente());
        verify(pagoRepository, times(1)).save(any(Pago.class));
    }

    @Test
    void testRegistrarPago_ClienteNoEncontrado() {
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> pagoService.registrarPago(1, pago));
    }

    @Test
    void testEliminar() {
        doNothing().when(pagoRepository).deleteById(1);

        pagoService.eliminar(1);

        verify(pagoRepository, times(1)).deleteById(1);
    }

}