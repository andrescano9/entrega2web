package co.javeriana.edu.ProyectoTransmilleno.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EstacionDTO {

    private Long id;
    private String nombre;

    public EstacionDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
