package co.javeriana.edu.ProyectoTransmilleno.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "asignacionRuta")
    @JsonBackReference("ruta-asignacion")
    private List<Asignacion> asignacionesRutas = new ArrayList<>();

    /* 
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ruta_id") // Agrega la columna de la llave foránea en la tabla Estacion
    private List<Estacion> estaciones = new ArrayList<>();
    */

    public Ruta(String nombre) {
        this.nombre = nombre;
    }

    /*public void addEstacion(Estacion estacion) {
        this.estaciones.add(estacion);
    } */
}
