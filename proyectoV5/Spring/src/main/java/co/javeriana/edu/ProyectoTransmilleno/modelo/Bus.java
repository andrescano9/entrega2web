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
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String placa;
    private String modelo;

    @OneToMany(mappedBy = "asignacionBus")
    @JsonBackReference("bus-asignacion")
    private List<Asignacion> asignacionesBus = new ArrayList<>();

    public Bus(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
    }
}
