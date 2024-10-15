package co.javeriana.edu.ProyectoTransmilleno.conversion;

import org.springframework.stereotype.Component;
import co.javeriana.edu.ProyectoTransmilleno.dto.BusDTO;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Bus;

@Component
public class BusDTOConverter {

    public BusDTO convertToDTO(Bus bus) {
        BusDTO dto = new BusDTO();
        dto.setId(bus.getId());
        dto.setPlaca(bus.getPlaca());
        dto.setModelo(bus.getModelo());
        return dto;
    }

    public Bus convertToEntity(BusDTO dto) {
        Bus bus = new Bus();
        bus.setId(dto.getId());
        bus.setPlaca(dto.getPlaca());
        bus.setModelo(dto.getModelo());
        return bus;
    }
}
