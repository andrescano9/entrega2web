package co.javeriana.edu.ProyectoTransmilleno.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.javeriana.edu.ProyectoTransmilleno.modelo.Bus;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.BusRepositorio;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepositorio busRepositorio;

    // Listar todos los buses
    public List<Bus> listarBuses() {
        return busRepositorio.findAll();
    }

    // Recuperar un bus por ID
    public Bus recuperarBus(Long id) {
        return busRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bus no encontrado"));
    }

    // Guardar o actualizar un bus
    public void guardarBus(Bus bus) {

        if (bus.getId() == null) {
            // Creación de nuevo bus
            if (busRepositorio.existsByPlaca(bus.getPlaca())) {
                throw new IllegalArgumentException("La placa ya está en uso");
            }
        } else {
            // Actualización de bus existente
            Bus existingBus = busRepositorio.findById(bus.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Bus no encontrado"));
            existingBus.setPlaca(bus.getPlaca());
            existingBus.setModelo(bus.getModelo());
            bus = existingBus;
        }

        busRepositorio.save(bus);
    }

    // Eliminar un bus
    public void eliminarBus(Long id) {
        Bus bus = busRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bus no encontrado"));

        if (!bus.getAsignacionesBus().isEmpty()) {
            throw new IllegalArgumentException("No se puede eliminar el bus porque tiene asignaciones.");
        }

        busRepositorio.delete(bus);
    }

    // Buscar buses por placa
    public List<Bus> buscarBusPorPlaca(String placa) {
        if (placa == null || placa.isEmpty()) {
            return busRepositorio.findAll();
        }
        return busRepositorio.findByPlacaContainingIgnoreCase(placa);
    }
}
