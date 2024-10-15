package co.javeriana.edu.ProyectoTransmilleno.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.javeriana.edu.ProyectoTransmilleno.modelo.Conductor;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.ConductorRepositorio;

@Service
public class ConductorService {

    @Autowired
    private ConductorRepositorio conductorRepositorio;

    // Listar todos los conductores
    public List<Conductor> listarConductores() {
        return conductorRepositorio.findAll();
    }

    // Recuperar un conductor por ID
    public Conductor recuperarConductor(Long id) {
        return conductorRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conductor no encontrado"));
    }

    // Guardar o actualizar un conductor
    public void guardarConductor(Conductor conductor) {
        if (conductor.getId() != null) {
            // Si el ID no es nulo, intentamos actualizar un conductor existente
            Conductor existingConductor = conductorRepositorio.findById(conductor.getId()).orElse(null);

            if (existingConductor != null) {
                // Verificar si la cédula ha sido modificada y si el nuevo número ya está en uso
                if (!existingConductor.getCc().equals(conductor.getCc())
                        && conductorRepositorio.existsByCc(conductor.getCc())) {
                    throw new IllegalArgumentException("El número de cédula ya está en uso");
                }

                // Verificar si el teléfono ha sido modificado y si el nuevo número ya está en uso
                if (!existingConductor.getPhone().equals(conductor.getPhone())
                        && conductorRepositorio.existsByPhone(conductor.getPhone())) {
                    throw new IllegalArgumentException("El número de teléfono ya está en uso");
                }
            }
        } else {
            // Si el ID es nulo, estamos creando un nuevo conductor, no se necesita verificación de duplicados
            if (conductorRepositorio.existsByCc(conductor.getCc())) {
                throw new IllegalArgumentException("El número de cédula ya está en uso");
            }

            if (conductorRepositorio.existsByPhone(conductor.getPhone())) {
                throw new IllegalArgumentException("El número de teléfono ya está en uso");
            }
        }

        // Guardar o actualizar el conductor
        conductorRepositorio.save(conductor);
    }

    // Eliminar un conductor
    public void eliminarConductor(Long id) {
        Conductor conductor = conductorRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conductor no encontrado"));

        if (!conductor.getAsignacionesConductor().isEmpty()) {
            throw new IllegalArgumentException("El conductor no puede ser eliminado porque tiene asignaciones activas.");
        }

        conductorRepositorio.delete(conductor);
    }

    // Buscar conductores por nombre
    public List<Conductor> buscarConductorPorNombre(String textoBusqueda) {
        return conductorRepositorio.findAllByName(textoBusqueda);
    }
}
