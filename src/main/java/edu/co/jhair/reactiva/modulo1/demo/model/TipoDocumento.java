package edu.co.jhair.reactiva.modulo1.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class TipoDocumento {

    @Id
    protected Integer id;
    private String tipoDocumento;
    private String descripcion;
}
