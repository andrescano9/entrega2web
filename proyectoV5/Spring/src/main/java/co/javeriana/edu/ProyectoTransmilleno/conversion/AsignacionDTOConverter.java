package co.javeriana.edu.ProyectoTransmilleno.conversion;

import org.springframework.stereotype.Component;
import co.javeriana.edu.ProyectoTransmilleno.dto.AsignacionDTO;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Asignacion;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Bus;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Conductor;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Horario;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Ruta;

@Component
public class AsignacionDTOConverter {

    public AsignacionDTO entityToDTO(Asignacion asignacion) {
        AsignacionDTO dto = new AsignacionDTO();
        dto.setId(asignacion.getId());
        dto.setBus(asignacion.getAsignacionBus()); // Cambiado para incluir el objeto Bus completo
        dto.setConductor(asignacion.getAsignacionConductor()); // Cambiado para incluir el objeto Conductor completo
        dto.setHorario(asignacion.getHorarioAsignado()); // Cambiado para incluir el objeto Horario completo
        dto.setRuta(asignacion.getAsignacionRuta()); // Cambiado para incluir el objeto Ruta completo
        return dto;
    }

    public Asignacion dtoToEntity(AsignacionDTO dto, Bus bus, Conductor conductor, Horario horario, Ruta ruta) {
        Asignacion asignacion = new Asignacion();
        asignacion.setId(dto.getId());
        asignacion.setAsignacionBus(bus); // Se establece el objeto Bus
        asignacion.setAsignacionConductor(conductor); // Se establece el objeto Conductor
        asignacion.setHorarioAsignado(horario); // Se establece el objeto Horario
        asignacion.setAsignacionRuta(ruta); // Se establece el objeto Ruta
        return asignacion;
    }
}
