package co.javeriana.edu.ProyectoTransmilleno.conversion;

import co.javeriana.edu.ProyectoTransmilleno.dto.HorarioDTO;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Horario;
import org.springframework.stereotype.Component;

@Component
public class HorarioDTOConverter {

    public HorarioDTO convertToDTO(Horario horario) {
        return new HorarioDTO(horario.getId(), horario.getHoraInicio(), horario.getHoraFin(), horario.getDiaSemana());
    }

    public Horario convertToEntity(HorarioDTO horarioDTO) {
        return new Horario(horarioDTO.getHoraInicio(), horarioDTO.getHoraFin(), horarioDTO.getDiaSemana());
    }
}