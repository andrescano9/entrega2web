package co.javeriana.edu.ProyectoTransmilleno.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class RutaDTO {

    private Long id;
    private String nombre;
    //private List<EstacionDTO> estaciones;  // Nueva lista de estaciones en el DTO

    /*
     public RutaDTO(Long id, String nombre, List<EstacionDTO> estaciones) {
        this.id = id;
        this.nombre = nombre;
        this.estaciones = estaciones;
    }
     */

     public RutaDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
