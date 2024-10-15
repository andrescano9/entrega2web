package co.javeriana.edu.ProyectoTransmilleno.controlador;

import co.javeriana.edu.ProyectoTransmilleno.conversion.RutaDTOConverter;
import co.javeriana.edu.ProyectoTransmilleno.dto.EstacionDTO;
import co.javeriana.edu.ProyectoTransmilleno.dto.RutaDTO;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Estacion;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Ruta;
import co.javeriana.edu.ProyectoTransmilleno.servicio.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.EstacionRepositorio;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rutas")
public class RutaControlador {

    @Autowired
    private RutaService rutaService;

    @Autowired
    private RutaDTOConverter rutaDTOConverter;

    @Autowired
    private EstacionRepositorio estacionRepositorio;

    @GetMapping("/list")
    public ResponseEntity<List<RutaDTO>> getAllRutas() {
        List<RutaDTO> rutas = rutaService.getAllRutas().stream()
                .map(rutaDTOConverter::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(rutas);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<RutaDTO> getRutaById(@PathVariable Long id) {
        Ruta ruta = rutaService.getRutaById(id);
        RutaDTO rutaDTO = rutaDTOConverter.convertToDTO(ruta);
        return ResponseEntity.status(HttpStatus.OK).body(rutaDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<RutaDTO> saveRuta(@Valid @RequestBody RutaDTO rutaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Ruta ruta = rutaDTOConverter.convertToEntity(rutaDTO);
        Ruta savedRuta = rutaService.createRuta(ruta);
        return ResponseEntity.status(HttpStatus.CREATED).body(rutaDTOConverter.convertToDTO(savedRuta));
    }

    @PutMapping("/edit-form/{id}")
    public ResponseEntity<RutaDTO> updateRuta(@PathVariable Long id, @Valid @RequestBody RutaDTO rutaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Ruta rutaDetails = rutaDTOConverter.convertToEntity(rutaDTO);
        Ruta updatedRuta = rutaService.updateRuta(id, rutaDetails);
        return ResponseEntity.status(HttpStatus.OK).body(rutaDTOConverter.convertToDTO(updatedRuta));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRuta(@PathVariable Long id) {
        rutaService.deleteRuta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    /* 
    @PostMapping("/agregar-estacion/{rutaId}")
    public ResponseEntity<RutaDTO> agregarEstacion(@PathVariable Long rutaId, @RequestBody EstacionDTO estacionDTO) {

        // Recuperar la ruta por ID
        Ruta ruta = rutaService.getRutaById(rutaId);

        // Recuperar la estación desde la base de datos usando el ID de EstacionDTO
        Estacion estacion = estacionRepositorio.findById(estacionDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Estación no encontrada"));

        // Agregar la estación a la ruta
        ruta.addEstacion(estacion);

        // Guardar la ruta con la estación añadida
        rutaService.saveRuta(ruta); 

        // Devolver la respuesta con la ruta actualizada
        return ResponseEntity.status(HttpStatus.OK).body(rutaDTOConverter.convertToDTO(ruta));
    }

    */


}
