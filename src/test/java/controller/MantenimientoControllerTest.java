package controller;

import edu.co.jhair.reactiva.modulo1.demo.controller.MantenimientoController;
import edu.co.jhair.reactiva.modulo1.demo.model.Mantenimiento;
import edu.co.jhair.reactiva.modulo1.demo.service.MantenimientoService;
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
public class MantenimientoControllerTest {

    @Mock
    private MantenimientoService mantenimientoService;

    @InjectMocks
    private MantenimientoController mantenimientoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMantenimientoByIdExitoso() throws Exception {
        int id = 1;
        int idVehiculo = 1;
        Mantenimiento mantenimientoEsperado = new Mantenimiento(id, idVehiculo, 1, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 150000D, "");
        when(mantenimientoService.findById(any())).thenReturn(Mono.just(mantenimientoEsperado));
        Mono<Mantenimiento> resultado = mantenimientoController.getMantenimientoById(id);
        assertEquals(mantenimientoEsperado, resultado.block());
    }

    @Test
    public void testGetMantenimientoByIdNoEncontrado() {
        int id = 10;
        when(mantenimientoService.findById(id)).thenReturn(Mono.empty());
        when(mantenimientoService.findById(id)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Mantenimiento not found"));
        assertThrows(ResponseStatusException.class, () -> mantenimientoController.getMantenimientoById(id).block());
    }

    @Test
    void testGetAllMantenimientosExitoso() {
        Mantenimiento mantenimientoEsperado1 = new Mantenimiento(1, 1, 1, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 150000D, "");
        Mantenimiento mantenimientoEsperado2 = new Mantenimiento(2, 2, 2, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, 50000D, "");
        Flux<Mantenimiento> expectedMantenimiento = Flux.just(mantenimientoEsperado1, mantenimientoEsperado2);
        when(mantenimientoService.findAll()).thenReturn(expectedMantenimiento);
        Flux<Mantenimiento> resultado = mantenimientoController.getAllMantenimientos();
        resultado.subscribe();
        assertEquals(expectedMantenimiento, resultado);
    }

    @Test
    void createMantenimientoExitoso() {
        Mantenimiento mantenimientoEsperado = new Mantenimiento(1, 1, 1, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 150000D, "");
        when(mantenimientoService.saveMantenimiento(any(Mantenimiento.class))).thenReturn(Mono.just(mantenimientoEsperado));
        Mono<Mantenimiento> result = mantenimientoController.saveMantenimiento(mantenimientoEsperado);
        StepVerifier.create(result)
                .expectNext(mantenimientoEsperado)
                .verifyComplete();
    }

    @Test
    public void testCrearMantenimientoFallido() {
        Mantenimiento mantenimientoEsperado = new Mantenimiento(1, 1, 1, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, 150000D, "");
        when(mantenimientoService.saveMantenimiento(any(Mantenimiento.class))).thenReturn(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Mantenimiento no creado")));
        StepVerifier.create(mantenimientoController.saveMantenimiento(mantenimientoEsperado))
                .expectError(ResponseStatusException.class)
                .verify();
    }
}
