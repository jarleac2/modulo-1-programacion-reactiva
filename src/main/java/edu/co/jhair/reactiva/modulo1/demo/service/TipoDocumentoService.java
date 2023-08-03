package edu.co.jhair.reactiva.modulo1.demo.service;

import edu.co.jhair.reactiva.modulo1.demo.model.TipoDocumento;
import edu.co.jhair.reactiva.modulo1.demo.model.Vehiculo;
import edu.co.jhair.reactiva.modulo1.demo.repository.TipoDocumentoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TipoDocumentoService {

    private final TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoService(TipoDocumentoRepository tipoDocumentoRepository){
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    public Flux<TipoDocumento> findAll(){
        return tipoDocumentoRepository.findAll();
    }

    public Mono<TipoDocumento> findById(Integer id){
        return tipoDocumentoRepository.findById(id);
    }
}
