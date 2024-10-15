package co.javeriana.edu.ProyectoTransmilleno.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.javeriana.edu.ProyectoTransmilleno.modelo.Asignacion;

@Repository
public interface AsignacionRepositorio extends JpaRepository<Asignacion, Long>{

    List<Asignacion> findByAsignacionBusId(Long busId);
    
}
