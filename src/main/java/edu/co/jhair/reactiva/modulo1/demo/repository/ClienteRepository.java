package edu.co.jhair.reactiva.modulo1.demo.repository;

import edu.co.jhair.reactiva.modulo1.demo.model.Cliente;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ClienteRepository extends R2dbcRepository<Cliente, Integer> {

    Flux<Cliente> findByNombre(String nombres);

    Flux<Cliente> findByActivo(boolean activo);

    Flux<Cliente> findByNombreAndActivo(String nombres, boolean activo);
}
