package edu.co.jhair.reactiva.modulo1.demo.repository;

import edu.co.jhair.reactiva.modulo1.demo.model.Mantenimiento;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantenimientoRepository extends R2dbcRepository<Mantenimiento, Integer> {

}
