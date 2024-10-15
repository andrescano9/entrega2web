package co.javeriana.edu.ProyectoTransmilleno.conversion;

import org.springframework.stereotype.Component;

import co.javeriana.edu.ProyectoTransmilleno.dto.ConductorDTO;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Conductor;

@Component
public class ConductorDTOConverter {

    public ConductorDTO convertToDTO(Conductor conductor) {
        ConductorDTO dto = new ConductorDTO();
        dto.setId(conductor.getId());
        dto.setName(conductor.getName());
        dto.setCc(conductor.getCc());
        dto.setPhone(conductor.getPhone());
        dto.setAdress(conductor.getAdress());
        return dto;
    }

    public Conductor convertToEntity(ConductorDTO dto) {
        Conductor conductor = new Conductor();
        conductor.setId(dto.getId());
        conductor.setName(dto.getName());
        conductor.setCc(dto.getCc());
        conductor.setPhone(dto.getPhone());
        conductor.setAdress(dto.getAdress());
        return conductor;
    }
}