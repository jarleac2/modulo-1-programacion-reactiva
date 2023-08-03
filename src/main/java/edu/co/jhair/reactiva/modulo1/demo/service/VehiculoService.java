package edu.co.jhair.reactiva.modulo1.demo.service;

import edu.co.jhair.reactiva.modulo1.demo.model.Vehiculo;
import edu.co.jhair.reactiva.modulo1.demo.repository.VehiculoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository){
        this.vehiculoRepository = vehiculoRepository;
    }
    public Mono<Vehiculo> findById(Integer id){
        return vehiculoRepository.findById(id);
    }
    public Flux<Vehiculo> findAll(){
        return vehiculoRepository.findAll();
    }
    public Mono<Vehiculo> save(Vehiculo cliente){
        return vehiculoRepository.save(cliente);
    }

    public Flux<Vehiculo> findByMarca(String marca){
        return vehiculoRepository.findByMarca(marca);
    }

    public Flux<Vehiculo> findByPlaca(String placa){
        return vehiculoRepository.findByPlaca(placa);
    }

    public Mono<Vehiculo> deleteById(Integer id){
        return vehiculoRepository.findById(id).flatMap(cliente -> vehiculoRepository.deleteById(cliente.getId())).thenReturn(null);
    }

    public Mono<Void> deleteAll(){
        return vehiculoRepository.deleteAll();
    }

}
