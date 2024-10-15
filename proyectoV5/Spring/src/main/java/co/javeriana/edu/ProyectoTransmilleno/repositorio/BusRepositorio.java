package co.javeriana.edu.ProyectoTransmilleno.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.javeriana.edu.ProyectoTransmilleno.modelo.Bus;

@Repository
public interface BusRepositorio extends JpaRepository<Bus, Long>{

    boolean existsByPlaca(String placa);

    List<Bus> findByPlacaContainingIgnoreCase(String placa);

  
}
