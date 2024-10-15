package co.javeriana.edu.ProyectoTransmilleno.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import co.javeriana.edu.ProyectoTransmilleno.conversion.AsignacionDTOConverter;
import co.javeriana.edu.ProyectoTransmilleno.dto.AsignacionDTO;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Asignacion;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Bus;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Conductor;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Horario;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Ruta;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.AsignacionRepositorio;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.BusRepositorio;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.ConductorRepositorio;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.HorarioRepositorio;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.RutaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class AsignacionService {

    @Autowired
    private BusRepositorio busRepositorio;

    @Autowired
    private AsignacionRepositorio asignacionRepositorio;

    @Autowired
    private ConductorRepositorio conductorRepositorio;

    @Autowired
    private HorarioRepositorio horarioRepositorio;

    @Autowired
    private RutaRepositorio rutaRepositorio;

    @Autowired
    private AsignacionDTOConverter asignacionDTOConverter;

    public AsignacionDTO buscarAsignacionDTO(Long id) {
        Asignacion asignacion = asignacionRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada."));
        return asignacionDTOConverter.entityToDTO(asignacion);
    }

    public List<AsignacionDTO> listarAsignacionesDTO() {
        return asignacionRepositorio.findAll().stream()
                .map(asignacionDTOConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    public boolean verificarAsignacionBus(Long busId, Long horarioId) {
        return asignacionRepositorio.findByAsignacionBusId(busId).stream()
                .anyMatch(asignacion -> asignacion.getHorario().getId().equals(horarioId));
    }

    @Transactional
    public AsignacionDTO crearAsignacionDTO(Long idBus, Long idConductor, Long idHorario, Long idRuta) {
        if (verificarAsignacionBus(idBus, idHorario)) {
            throw new RuntimeException("El bus ya está asignado en este horario.");
        }

        Bus bus = busRepositorio.findById(idBus)
                .orElseThrow(() -> new RuntimeException("Bus no encontrado."));
        Conductor conductor = conductorRepositorio.findById(idConductor)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado."));
        Horario horario = horarioRepositorio.findById(idHorario)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado."));
        Ruta ruta = rutaRepositorio.findById(idRuta)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada."));

        Asignacion asignacion = new Asignacion();
        asignacion.asociarBus(bus);
        asignacion.asociarConductor(conductor);
        asignacion.asociarHorario(horario);
        asignacion.asociarRuta(ruta);

        Asignacion savedAsignacion = asignacionRepositorio.save(asignacion);
        return asignacionDTOConverter.entityToDTO(savedAsignacion);
    }

    
      public AsignacionDTO guardarAsignacionDTO(AsignacionDTO asignacionDTO) {
        Bus bus = busRepositorio.findById(asignacionDTO.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus no encontrado."));
        Conductor conductor = conductorRepositorio.findById(asignacionDTO.getConductorId())
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado."));
        Horario horario = horarioRepositorio.findById(asignacionDTO.getHorarioId())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado."));
        Ruta ruta = rutaRepositorio.findById(asignacionDTO.getRutaId())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada."));

        Asignacion asignacion = asignacionDTOConverter.dtoToEntity(asignacionDTO, bus, conductor, horario, ruta);
        Asignacion savedAsignacion = asignacionRepositorio.save(asignacion);
        return asignacionDTOConverter.entityToDTO(savedAsignacion);
    }
     

    public AsignacionDTO actualizarAsignacionDTO(Long id, AsignacionDTO asignacionDTO) {
        // Buscar la asignación existente
        Asignacion asignacionExistente = asignacionRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada."));
    
        // Obtener los elementos relacionados
        Bus bus = busRepositorio.findById(asignacionDTO.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus no encontrado."));
        Conductor conductor = conductorRepositorio.findById(asignacionDTO.getConductorId())
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado."));
        Horario horario = horarioRepositorio.findById(asignacionDTO.getHorarioId())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado."));
        Ruta ruta = rutaRepositorio.findById(asignacionDTO.getRutaId())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada."));
    
        // Actualizar los campos de la asignación existente
        asignacionExistente.setAsignacionBus(bus);
        asignacionExistente.setAsignacionConductor(conductor);
        asignacionExistente.setAsignacionRuta(ruta);
        asignacionExistente.setHorarioAsignado(horario);
        
            
        // Guardar la asignación actualizada
        Asignacion savedAsignacion = asignacionRepositorio.save(asignacionExistente);
        
        // Convertir a DTO y retornar
        return asignacionDTOConverter.entityToDTO(savedAsignacion);
    }

    
    

    public void eliminarAsignacion(Long id) {
        if (!asignacionRepositorio.existsById(id)) {
            throw new RuntimeException("Asignación no encontrada.");
        }
        asignacionRepositorio.deleteById(id);
    }

    @Transactional
    public void asociarConductorAAsignacion(Long idAsignacion, Long idConductor) {
        Asignacion asignacion = asignacionRepositorio.findById(idAsignacion)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada."));
        Conductor conductor = conductorRepositorio.findById(idConductor)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado."));

        asignacion.asociarConductor(conductor);
        asignacionRepositorio.save(asignacion);
    }

    @Transactional
    public void asociarBus(Long idAsignacion, Long idBus) {
        Asignacion asignacion = asignacionRepositorio.findById(idAsignacion)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada."));
        Bus bus = busRepositorio.findById(idBus)
                .orElseThrow(() -> new RuntimeException("Bus no encontrado."));

        asignacion.asociarBus(bus);
        asignacionRepositorio.save(asignacion);
    }

    @Transactional
    public void asociarHorario(Long idAsignacion, Long idHorario) {
        Asignacion asignacion = asignacionRepositorio.findById(idAsignacion)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada."));
        Horario horario = horarioRepositorio.findById(idHorario)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado."));

        asignacion.asociarHorario(horario);
        asignacionRepositorio.save(asignacion);
    }

    @Transactional
    public void asociarRuta(Long idAsignacion, Long idRuta) {
        Asignacion asignacion = asignacionRepositorio.findById(idAsignacion)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada."));
        Ruta ruta = rutaRepositorio.findById(idRuta)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada."));

        asignacion.asociarRuta(ruta);
        asignacionRepositorio.save(asignacion);
    }
}
