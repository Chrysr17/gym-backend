package com.example.gimnasio.service;

import com.example.gimnasio.entity.Cliente;
import com.example.gimnasio.repository.ClienteRepository;
import com.example.gimnasio.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

}