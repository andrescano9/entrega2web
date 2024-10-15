package co.javeriana.edu.ProyectoTransmilleno.servicio;

import co.javeriana.edu.ProyectoTransmilleno.modelo.Ruta;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.RutaRepositorio;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutaService {

    @Autowired
    private RutaRepositorio rutaRepositorio;

    public List<Ruta> getAllRutas() {
        return rutaRepositorio.findAll();
    }

    public Ruta createRuta(Ruta ruta) {
        return rutaRepositorio.save(ruta);
    }

    public Ruta updateRuta(Long id, Ruta rutaDetails) {
        Ruta ruta = rutaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con ID: " + id));

        // Actualiza el campo nombre
        ruta.setNombre(rutaDetails.getNombre());

        // No hay otros campos que actualizar, así que solo guardamos la ruta
        return rutaRepositorio.save(ruta);
    }


    public void deleteRuta(Long id) {
        Ruta ruta = rutaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con ID: " + id));
        rutaRepositorio.delete(ruta);
    }

     public Ruta getRutaById(Long id) {
    return rutaRepositorio.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Ruta no encontrada"));
}

    public Ruta saveRuta(Ruta ruta) {
        return rutaRepositorio.save(ruta);
    }

}
