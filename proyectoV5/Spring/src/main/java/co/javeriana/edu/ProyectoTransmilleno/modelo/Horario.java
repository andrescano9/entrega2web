package co.javeriana.edu.ProyectoTransmilleno.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String horaInicio;
    private String horaFin;
    private List<String> diaSemana = new ArrayList<>();

    @OneToMany(mappedBy = "horarioAsignado")
    @JsonBackReference("horario-asignacion")
    private List<Asignacion> asignacionHorarios = new ArrayList<>();

    public Horario(String horaInicio, String horaFin, List<String> diaSemana) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diaSemana = diaSemana;
    }
}
