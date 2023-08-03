package edu.co.jhair.reactiva.modulo1.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
@AllArgsConstructor
public class Mantenimiento {

    @Id
    private Integer id;
    private Integer idVehiculo;
    private Integer codigoRevision;
    private Boolean cambioAceite;
    private Boolean cambioFiltros;
    private Boolean revisionFrenos;
    private Double precio;
    private String otros;
}
