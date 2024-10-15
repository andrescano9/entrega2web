package co.javeriana.edu.ProyectoTransmilleno.conversion;

import co.javeriana.edu.ProyectoTransmilleno.dto.RutaDTO;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Ruta;
import org.springframework.stereotype.Component;

@Component
public class RutaDTOConverter {

    public RutaDTO convertToDTO(Ruta ruta) {
        return new RutaDTO(ruta.getId(), ruta.getNombre());
    }

    public Ruta convertToEntity(RutaDTO rutaDTO) {
        return new Ruta(rutaDTO.getNombre());
    }
}