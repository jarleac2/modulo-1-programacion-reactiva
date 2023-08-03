package controller;

import edu.co.jhair.reactiva.modulo1.demo.controller.ClienteController;
import edu.co.jhair.reactiva.modulo1.demo.model.Cliente;
import edu.co.jhair.reactiva.modulo1.demo.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetClienteByIdExitoso() throws Exception {
        int id = 56;
        int idTipoDocumento = 1;
        Cliente clienteEsperado = new Cliente(id, idTipoDocumento, "1098123123", "Orlando", "Parra", "3133133132", "test@test.co", true);
        when(clienteService.findById(any())).thenReturn(Mono.just(clienteEsperado));
        Mono<Cliente> resultado = clienteController.getClienteById(id);
        assertEquals(clienteEsperado, resultado.block());
    }

    @Test
    public void testGetClienteByIdNoEncontrado() {
        int id = 100;
        when(clienteService.findById(id)).thenReturn(Mono.empty());
        when(clienteService.findById(id)).thenThrow(new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Cliente not found"));
        assertThrows(ResponseStatusException.class, () -> clienteController.getClienteById(id).block());
    }

    @Test
    void testGetAllClientesExitoso() {
        Cliente clienteEsperado = new Cliente(1, 1, "1098123123", "Orlando", "Parra", "3133133132", "test@test.co", true);
        Cliente clienteEsperado2 = new Cliente(2, 1, "1098312312", "John", "Paredes", "3133133134", "test2@test.co", false);
        Flux<Cliente> expectedClientes = Flux.just(clienteEsperado, clienteEsperado2);
        when(clienteService.findAll()).thenReturn(expectedClientes);
        Flux<Cliente> resultado = clienteController.getAllClientes();
        resultado.subscribe();
        assertEquals(expectedClientes, resultado);
    }

    @Test
    void createClienteExitoso() {
        Cliente clienteEsperado = new Cliente(1, 1, "1098123123", "Orlando", "Parra", "3133133132", "test@test.co", true);
        when(clienteService.save(any(Cliente.class))).thenReturn(Mono.just(clienteEsperado));
        Mono<Cliente> result = clienteController.createCliente(clienteEsperado);
        StepVerifier.create(result)
                .expectNext(clienteEsperado)
                .verifyComplete();
    }

    @Test
    public void testCreateClienteFallido() {
        Cliente clienteEsperado = new Cliente(1, 1, "1098123123", "Orlando", "Parra", "3133133132", "test@test.co", true);
        when(clienteService.save(any(Cliente.class))).thenReturn(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no creado")));
        StepVerifier.create(clienteController.createCliente(clienteEsperado))
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    void testUpdateClienteExitoso() {
        Cliente clienteActualizado = new Cliente(1, 1, "1098123123", "Orlando", "Parra", "3133133132", "test@test.co", true);
        when(clienteService.update(any(Integer.class), any(Cliente.class))).thenReturn(Mono.just(clienteActualizado));
        Mono<Cliente> result = clienteController.updateCliente(clienteActualizado.getId(), clienteActualizado);
        StepVerifier.create(result)
                .expectNext(clienteActualizado)
                .verifyComplete();
    }

    @Test
    public void testUpdateClienteFallido() {
        Cliente clienteActualizado = new Cliente(1, 1, "1098123123", "Orlando", "Parra", "3133133132", "test@test.co", true);
        when(clienteService.update(any(Integer.class), any(Cliente.class))).thenReturn(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no actualizado")));
        StepVerifier.create(clienteController.updateCliente(clienteActualizado.getId(), clienteActualizado))
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    void testDeleteClienteByIdExitoso() throws Exception {
        int id = 1;
        Cliente clienteXBorrar = new Cliente(id, 1, "1098123123", "Orlando", "Parra", "3133133132", "test@test.co", true);
        when(clienteService.deleteById(any())).thenReturn(Mono.just(clienteXBorrar));
        Mono<Cliente> resultado = clienteController.deleteClienteById(id);
        assertEquals(clienteXBorrar, resultado.block());
    }

    @Test
    public void testDeleteClienteByIdFallido() {
        int id = 100;
        when(clienteService.deleteById(id)).thenReturn(Mono.empty());
        when(clienteService.deleteById(id)).thenThrow(new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Cliente no borrado"));
        assertThrows(ResponseStatusException.class, () -> clienteController.deleteClienteById(id).block());
    }

    @Test
    void testDeleteAllClientesExitoso() {
        when(clienteService.deleteAll()).thenReturn(Mono.empty());
        Mono<Void> resultado = clienteController.deleteAllClientes();
        resultado.subscribe();
        assertEquals(Mono.empty(), resultado);
    }
}
