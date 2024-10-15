package co.javeriana.edu.ProyectoTransmilleno.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.javeriana.edu.ProyectoTransmilleno.modelo.Ruta;;


@Repository
public interface RutaRepositorio extends JpaRepository<Ruta,Long>{
    
}
