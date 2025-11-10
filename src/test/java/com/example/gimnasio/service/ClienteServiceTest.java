package com.example.gimnasio.service;

import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.repository.ClienteRepository;
import com.example.gimnasio.service.impl.ClienteServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setClienteId(1);
        cliente.setNombre("Juan Pérez");
        cliente.setDni("12345678");
        cliente.setTelefono("987654321");
        cliente.setCorreo("juan@example.com");
        cliente.setDireccion("Av. Lima 123");
        cliente.setFechaPago(LocalDate.now());
        cliente.setMensualidad(BigDecimal.valueOf(150.00));
        cliente.setDescripcion("Cliente activo");
        cliente.setEliminado(false);
    }
    @Test
    void testListarTodos() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> result = clienteService.listarTodos();

        assertEquals(1, result.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId_Econtrado(){
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.buscarPorId(1);

        assertTrue(result.isPresent());
        assertEquals("Juan Pérez" , result.get().getNombre());

    }

    @Test
    void testBuscarPorId_NoEncontrado() {
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        Optional<Cliente> result = clienteService.buscarPorId(1);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGuardar() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente result = clienteService.guardar(cliente);

        assertNotNull(result);
        assertEquals("Juan Pérez", result.getNombre());
        verify(clienteRepository).save(cliente);
    }

    @Test
    void testActualizarCliente_Exitoso() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente actualizado = new Cliente();
        actualizado.setNombre("Pedro Ramos");
        actualizado.setDni("87654321");

        Cliente result = clienteService.actualizarCliente(1, actualizado);

        assertEquals("Pedro Ramos", result.getNombre());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testActualizarCliente_NoEncontrado() {
        when(clienteRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clienteService.actualizarCliente(99, cliente));
    }

    @Test
    void testEliminarLogico() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        clienteService.eliminarLogico(1);

        assertTrue(cliente.isEliminado());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testEliminarLogico_NoEncontrado() {
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clienteService.eliminarLogico(1));
    }


}