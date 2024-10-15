package co.javeriana.edu.ProyectoTransmilleno.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import co.javeriana.edu.ProyectoTransmilleno.modelo.Conductor;


@Repository
public interface ConductorRepositorio extends JpaRepository<Conductor, Long>{

    

    List<Conductor> findAllByName(String text);

    List<Conductor> findAllByNameStartingWith(String text);

    List<Conductor> findAllByNameStartingWithIgnoreCase (String text);
    
    
    @Query("SELECT c FROM Conductor c WHERE c.name LIKE concat(:text, '%')")
    List<Conductor> findPersonsByNameStartingWith(String text);
    
    @Query("SELECT c FROM Conductor c WHERE LOWER(c.name) LIKE LOWER(concat(:text, '%'))")
    List<Conductor> findPersonsByNameStartingWithCaseInsensitive(String text);

    boolean existsByCc(Integer cc); 
    boolean existsByPhone(Integer phone);

}