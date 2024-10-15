package co.javeriana.edu.ProyectoTransmilleno.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HorarioDTO {
    private Long id;
    private String horaInicio;
    private String horaFin;
    private List<String> diaSemana;

    public HorarioDTO(Long id, String horaInicio, String horaFin, List<String> diaSemana) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diaSemana = diaSemana;
    }
}
