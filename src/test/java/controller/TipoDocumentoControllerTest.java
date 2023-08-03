package controller;

import edu.co.jhair.reactiva.modulo1.demo.controller.TipoDocumentoController;
import edu.co.jhair.reactiva.modulo1.demo.model.TipoDocumento;
import edu.co.jhair.reactiva.modulo1.demo.service.TipoDocumentoService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TipoDocumentoControllerTest {

    @Mock
    private TipoDocumentoService tipoDocumentoService;

    @InjectMocks
    private TipoDocumentoController tipoDocumentoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTipoDocumentoByIdExitoso() throws Exception {
        int id = 1;
        TipoDocumento tipoDocumentoEsperado = new TipoDocumento(id, "Cedula", "Cedula ciudadania");
        when(tipoDocumentoService.findById(any())).thenReturn(Mono.just(tipoDocumentoEsperado));
        Mono<TipoDocumento> resultado = tipoDocumentoController.getTipoDocumentoById(id);
        assertEquals(tipoDocumentoEsperado, resultado.block());
    }

    @Test
    public void testGetTipoDocumentoByIdNoEncontrado() {
        int id = 10;
        when(tipoDocumentoService.findById(id)).thenReturn(Mono.empty());
        when(tipoDocumentoService.findById(id)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo Documento not found"));
        assertThrows(ResponseStatusException.class, () -> tipoDocumentoController.getTipoDocumentoById(id).block());
    }

    @Test
    void testGetAllTipoDocumentosExitoso() {
        TipoDocumento tipoDocumentoEsperado1 = new TipoDocumento(1, "Cedula", "Cedula ciudadania");
        TipoDocumento tipoDocumentoEsperado2 = new TipoDocumento(2, "Cedula extranjeria", "Cedula extranjeria");
        Flux<TipoDocumento> expectedTipoDocumento = Flux.just(tipoDocumentoEsperado1, tipoDocumentoEsperado2);
        when(tipoDocumentoService.findAll()).thenReturn(expectedTipoDocumento);
        Flux<TipoDocumento> resultado = tipoDocumentoController.getAllTipoDocumento();
        resultado.subscribe();
        assertEquals(expectedTipoDocumento, resultado);
    }
}
