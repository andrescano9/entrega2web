package co.javeriana.edu.ProyectoTransmilleno.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import co.javeriana.edu.ProyectoTransmilleno.dto.AsignacionDTO;
import co.javeriana.edu.ProyectoTransmilleno.servicio.AsignacionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/asignacion")
public class AsignacionControlador {

    @Autowired
    private AsignacionService asignacionService;

    @GetMapping("/list")
    public ResponseEntity<List<AsignacionDTO>> listarAsignaciones() {
        List<AsignacionDTO> asignaciones = asignacionService.listarAsignacionesDTO();
        return ResponseEntity.status(HttpStatus.OK).body(asignaciones);
    }

    @PutMapping(value = "/edit-form/{idAsignacion}", consumes = "application/json")
    public ResponseEntity<Object> actualizarAsignacion(
            @PathVariable Long idAsignacion,
            @Valid @RequestBody AsignacionDTO asignacionDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        try {
            // Actualizar la asignación existente
            AsignacionDTO updatedAsignacion = asignacionService.actualizarAsignacionDTO(idAsignacion, asignacionDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedAsignacion);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al actualizar asignación: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

    @GetMapping("/view/{idAsignacion}")
    public ResponseEntity<AsignacionDTO> recuperarAsignacion(@PathVariable Long idAsignacion) {
        AsignacionDTO asignacionDTO = asignacionService.buscarAsignacionDTO(idAsignacion);
        return ResponseEntity.status(HttpStatus.OK).body(asignacionDTO);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarAsignacion(@PathVariable Long id) {
        asignacionService.eliminarAsignacion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/save")
    public ResponseEntity<AsignacionDTO> guardarAsignacion(
            @Valid @RequestBody AsignacionDTO asignacionDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        AsignacionDTO savedAsignacion = asignacionService.guardarAsignacionDTO(asignacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAsignacion);
    }
    /* 
    @PostMapping("/crear")
    public ResponseEntity<AsignacionDTO> crearAsignacion(
            @RequestParam Long busId,
            @RequestParam Long conductorId,
            @RequestParam Long horarioId,
            @RequestParam Long rutaId) {
        try {
            AsignacionDTO asignacionDTO = asignacionService.crearAsignacionDTO(busId, conductorId, horarioId, rutaId);
            return ResponseEntity.status(HttpStatus.CREATED).body(asignacionDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    @PostMapping("/crear")
    public ResponseEntity<AsignacionDTO> crearAsignacion(
            @RequestBody AsignacionDTO asignacionDTO) {
        try {
            // Aquí llamas a tu servicio para crear la asignación
            AsignacionDTO nuevaAsignacion = asignacionService.crearAsignacionDTO(
                    asignacionDTO.getBus().getId(), 
                    asignacionDTO.getConductor().getId(), 
                    asignacionDTO.getHorario().getId(), 
                    asignacionDTO.getRuta().getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAsignacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{idAsignacion}/add-conductor/{idConductor}")
    public ResponseEntity<Void> agregarConductor(
            @PathVariable Long idAsignacion,
            @PathVariable Long idConductor) {
        asignacionService.asociarConductorAAsignacion(idAsignacion, idConductor);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{idAsignacion}/add-bus/{idBus}")
    public ResponseEntity<Void> agregarBus(
            @PathVariable Long idAsignacion,
            @PathVariable Long idBus) {
        asignacionService.asociarBus(idAsignacion, idBus);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{idAsignacion}/add-horario/{idHorario}")
    public ResponseEntity<Void> agregarHorario(
            @PathVariable Long idAsignacion,
            @PathVariable Long idHorario) {
        asignacionService.asociarHorario(idAsignacion, idHorario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{idAsignacion}/add-ruta/{idRuta}")
    public ResponseEntity<Void> agregarRuta(
            @PathVariable Long idAsignacion,
            @PathVariable Long idRuta) {
        asignacionService.asociarRuta(idAsignacion, idRuta);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
