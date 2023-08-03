package edu.co.jhair.reactiva.modulo1.demo.repository;

import edu.co.jhair.reactiva.modulo1.demo.model.Vehiculo;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VehiculoRepository extends R2dbcRepository<Vehiculo, Integer> {

    Flux<Vehiculo> findByMarca(String marca);

    Flux<Vehiculo> findByPlaca(String placa);

}
