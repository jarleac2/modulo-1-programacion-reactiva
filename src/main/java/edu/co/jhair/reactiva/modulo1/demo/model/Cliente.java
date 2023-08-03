package edu.co.jhair.reactiva.modulo1.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Cliente {

    @Id
    private Integer id;
    private Integer idTipoDocumento;
    private String numeroIdentificacion;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private Boolean activo;

}
