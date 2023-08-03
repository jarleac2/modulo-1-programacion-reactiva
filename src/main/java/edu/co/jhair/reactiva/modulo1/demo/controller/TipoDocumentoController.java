package edu.co.jhair.reactiva.modulo1.demo.controller;

import edu.co.jhair.reactiva.modulo1.demo.model.TipoDocumento;
import edu.co.jhair.reactiva.modulo1.demo.service.TipoDocumentoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tipoDocumento")
public class TipoDocumentoController {

    TipoDocumentoService tipoDocumentoService;

    public TipoDocumentoController(TipoDocumentoService tipoDocumentoService){
        this.tipoDocumentoService = tipoDocumentoService;
    }
    @GetMapping("/")
    public Flux<TipoDocumento> getAllTipoDocumento(){
        return tipoDocumentoService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<TipoDocumento> getTipoDocumentoById(@PathVariable Integer id){
        return tipoDocumentoService.findById(id);
    }
}