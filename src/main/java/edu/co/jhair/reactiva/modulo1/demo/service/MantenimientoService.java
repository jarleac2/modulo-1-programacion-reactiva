package edu.co.jhair.reactiva.modulo1.demo.service;

import edu.co.jhair.reactiva.modulo1.demo.model.Mantenimiento;
import edu.co.jhair.reactiva.modulo1.demo.repository.MantenimientoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;

    public MantenimientoService(MantenimientoRepository mantenimientoRepository){
        this.mantenimientoRepository = mantenimientoRepository;
    }

    public Flux<Mantenimiento> findAll(){
        return mantenimientoRepository.findAll();
    }

    public Mono<Mantenimiento> saveMantenimiento(Mantenimiento mantenimiento){
        return mantenimientoRepository.save(mantenimiento);
    }

    public Mono<Mantenimiento> findById(Integer id){
        return mantenimientoRepository.findById(id);
    }
}
