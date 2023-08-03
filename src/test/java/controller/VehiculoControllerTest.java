package controller;

import edu.co.jhair.reactiva.modulo1.demo.controller.VehiculoController;
import edu.co.jhair.reactiva.modulo1.demo.model.Vehiculo;
import edu.co.jhair.reactiva.modulo1.demo.service.VehiculoService;
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
public class VehiculoControllerTest {

    @Mock
    private VehiculoService vehiculoService;

    @InjectMocks
    private VehiculoController vehiculoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetVehiculoByIdExitoso() throws Exception {
        int id = 1;
        int idCliente = 1;
        Vehiculo vehiculoEsperado = new Vehiculo(id, idCliente, "Renault", "XVX123", "2014", "SANDERO");
        when(vehiculoService.findById(any())).thenReturn(Mono.just(vehiculoEsperado));
        Mono<Vehiculo> resultado = vehiculoController.getVehiculoById(id);
        assertEquals(vehiculoEsperado, resultado.block());
    }

    @Test
    public void testGetVehiculoByIdNoEncontrado() {
        int id = 100;
        when(vehiculoService.findById(id)).thenReturn(Mono.empty());
        when(vehiculoService.findById(id)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo not found"));
        assertThrows(ResponseStatusException.class, () -> vehiculoController.getVehiculoById(id).block());
    }

    @Test
    void testGetAllVehiculosExitoso() {
        Vehiculo vehiculoEsperado = new Vehiculo(1, 1, "Renault", "XVX123", "2014", "SANDERO");
        Vehiculo vehiculoEsperado2 = new Vehiculo(2, 2, "Chevrolet", "XVX123", "2014", "CRUZE");
        Flux<Vehiculo> expectedVehiculos = Flux.just(vehiculoEsperado, vehiculoEsperado2);
        when(vehiculoService.findAll()).thenReturn(expectedVehiculos);
        Flux<Vehiculo> resultado = vehiculoController.getAllVehiculos();
        resultado.subscribe();
        assertEquals(expectedVehiculos, resultado);
    }

    @Test
    void createVehiculoExitoso() {
        Vehiculo vehiculoEsperado = new Vehiculo(1, 1, "Renault", "XVX123", "2014", "SANDERO");
        when(vehiculoService.save(any(Vehiculo.class))).thenReturn(Mono.just(vehiculoEsperado));
        Mono<Vehiculo> result = vehiculoController.createVehiculo(vehiculoEsperado);
        StepVerifier.create(result)
                .expectNext(vehiculoEsperado)
                .verifyComplete();
    }

    @Test
    public void testCreateVehiculoFallido() {
        Vehiculo vehiculoEsperado = new Vehiculo(1, 1, "Renault", "XVX123", "2014", "SANDERO");
        when(vehiculoService.save(any(Vehiculo.class))).thenReturn(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no creado")));
        StepVerifier.create(vehiculoController.createVehiculo(vehiculoEsperado))
                .expectError(ResponseStatusException.class)
                .verify();
    }

    @Test
    void testDeleteVehiculoByIdExitoso() throws Exception {
        int id = 1;
        Vehiculo vehiculoXBorrar = new Vehiculo(id, 1, "Renault", "XVX123", "2014", "SANDERO");
        when(vehiculoService.deleteById(any())).thenReturn(Mono.just(vehiculoXBorrar));
        Mono<Vehiculo> resultado = vehiculoController.deleteVehiculoById(id);
        assertEquals(vehiculoXBorrar, resultado.block());
    }

    @Test
    public void testDeleteVehiculoByIdFallido() {
        int id = 100;
        when(vehiculoService.deleteById(id)).thenReturn(Mono.empty());
        when(vehiculoService.deleteById(id)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no borrado"));
        assertThrows(ResponseStatusException.class, () -> vehiculoController.deleteVehiculoById(id).block());
    }

    @Test
    void testDeleteAllVehiculosExitoso() {
        when(vehiculoService.deleteAll()).thenReturn(Mono.empty());
        Mono<Void> resultado = vehiculoController.deleteAllVehiculos();
        resultado.subscribe();
        assertEquals(Mono.empty(), resultado);
    }
}
