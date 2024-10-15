package co.javeriana.edu.ProyectoTransmilleno.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    //@JsonManagedReference("horario-asignacion")
    private Horario horarioAsignado;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    //@JsonManagedReference("bus-asignacion")
    private Bus asignacionBus;

    @ManyToOne
    @JoinColumn(name = "conductor_id")
    //@JsonManagedReference("conductor-asignacion")
    private Conductor asignacionConductor;

    @ManyToOne
    @JoinColumn(name = "ruta_id")
    //@JsonManagedReference("ruta-asignacion")
    private Ruta asignacionRuta;

    // Métodos asociar*
    public boolean asociarBus(Bus bus) {
        this.asignacionBus = bus;
        return true;
    }

    public boolean asociarConductor(Conductor conductor) {
        this.asignacionConductor = conductor;
        return true;
    }

    public boolean asociarHorario(Horario horario) {
        this.horarioAsignado = horario;
        return true;
    }

    public boolean asociarRuta(Ruta ruta) {
        this.asignacionRuta = ruta;
        return true;
    }

    public Horario getHorario() {
        return this.horarioAsignado;
    }
}
