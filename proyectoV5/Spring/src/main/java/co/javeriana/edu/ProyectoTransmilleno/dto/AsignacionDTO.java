package co.javeriana.edu.ProyectoTransmilleno.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.javeriana.edu.ProyectoTransmilleno.modelo.Bus;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Conductor;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Horario;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Ruta;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class AsignacionDTO {
    private Long id;
    private Bus bus; // Cambiado de Long a Bus
    private Conductor conductor; // Cambiado de Long a Conductor
    private Horario horario; // Cambiado de Long a Horario
    private Ruta ruta; // Cambiado de Long a Ruta

    @JsonIgnore
    public Long getBusId() {
        return bus != null ? bus.getId() : null; // Asegúrate de que la clase Bus tenga un método getId()
    }

    @JsonIgnore
    public Long getConductorId() {
        return conductor != null ? conductor.getId() : null; // Asegúrate de que la clase Conductor tenga un método getId()
    }
    
    @JsonIgnore
    public Long getHorarioId() {
        return horario != null ? horario.getId() : null; // Asegúrate de que la clase Horario tenga un método getId()
    }

    @JsonIgnore
    public Long getRutaId() {
        return ruta != null ? ruta.getId() : null; // Asegúrate de que la clase Ruta tenga un método getId()
    }
}
