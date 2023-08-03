package edu.co.jhair.reactiva.modulo1.demo.repository;

import edu.co.jhair.reactiva.modulo1.demo.model.TipoDocumento;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends R2dbcRepository<TipoDocumento, Integer> {

}
