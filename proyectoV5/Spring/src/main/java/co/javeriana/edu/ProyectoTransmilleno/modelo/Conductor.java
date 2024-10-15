package co.javeriana.edu.ProyectoTransmilleno.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
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
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cc", unique = true, nullable = false)
    private Integer cc;

    @Column(name = "phone", unique = true, nullable = false)
    private Integer phone;

    @Column(name = "adress", nullable = false)
    private String adress;

    @OneToMany(mappedBy = "asignacionConductor")
    @JsonBackReference("conductor-asignacion")
    private List<Asignacion> asignacionesConductor = new ArrayList<>();

    public Conductor(String name, Integer cc, Integer phone, String adress) {
        this.name = name;
        this.cc = cc;
        this.phone = phone;
        this.adress = adress;
    }
}
