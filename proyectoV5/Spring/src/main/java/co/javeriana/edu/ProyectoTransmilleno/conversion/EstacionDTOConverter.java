package co.javeriana.edu.ProyectoTransmilleno.conversion;

import co.javeriana.edu.ProyectoTransmilleno.dto.EstacionDTO;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Estacion;
import org.springframework.stereotype.Component;

@Component
public class EstacionDTOConverter {

    public EstacionDTO convertToDTO(Estacion estacion) {
        return new EstacionDTO(estacion.getId(), estacion.getNombre());
    }

    public Estacion convertToEntity(EstacionDTO estacionDTO) {
        return new Estacion(estacionDTO.getNombre());
    }
}
