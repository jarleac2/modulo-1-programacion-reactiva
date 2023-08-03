package edu.co.jhair.reactiva.modulo1.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Vehiculo {

    @Id
    private Integer id;
    private Integer idCliente;
    private String marca;
    private String placa;
    private String modelo;
    private String linea;
}
