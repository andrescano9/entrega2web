package co.javeriana.edu.ProyectoTransmilleno.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import co.javeriana.edu.ProyectoTransmilleno.dto.ConductorDTO;
import co.javeriana.edu.ProyectoTransmilleno.conversion.ConductorDTOConverter;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Conductor;
import co.javeriana.edu.ProyectoTransmilleno.servicio.ConductorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/conductor") // Cambia esta ruta según sea necesario
public class ConductorControlador {

    @Autowired
    private ConductorService conductorService;

    @Autowired
    private ConductorDTOConverter dtoConverter;

    // Listar todos los conductores
    @GetMapping("/list")
    public ResponseEntity<List<ConductorDTO>> listarConductores() {
        List<ConductorDTO> conductores = conductorService.listarConductores()
                .stream()
                .map(dtoConverter::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(conductores);
    }

    // Recuperar un conductor por ID
    @GetMapping("/view/{idConductor}")
    public ResponseEntity<ConductorDTO> recuperarConductor(@PathVariable Long idConductor) {
        Conductor conductor = conductorService.recuperarConductor(idConductor);
        ConductorDTO conductorDTO = dtoConverter.convertToDTO(conductor);
        return ResponseEntity.status(HttpStatus.OK).body(conductorDTO);
    }

    // Actualizar un conductor
    @PutMapping("/edit/{idConductor}")
    public ResponseEntity<Object> actualizarConductor(
            @PathVariable Long idConductor,
            @Valid @RequestBody ConductorDTO conductorDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        try {
            // Verificar si el conductor existe
            Conductor conductorExistente = conductorService.recuperarConductor(idConductor);
            if (conductorExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            // Convertir DTO a entidad y guardar
            Conductor conductor = dtoConverter.convertToEntity(conductorDTO);
            conductor.setId(idConductor);
            conductorService.guardarConductor(conductor);

            return ResponseEntity.status(HttpStatus.OK).body(dtoConverter.convertToDTO(conductor));
        } catch (IllegalArgumentException e) {
            System.err.println("Error al actualizar conductor: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

    // Guardar un conductor
    @PostMapping("/save")
    public ResponseEntity<Object> guardarConductor(
            @Valid @RequestBody ConductorDTO conductorDTO, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        try {
            Conductor conductor = dtoConverter.convertToEntity(conductorDTO);
            conductorService.guardarConductor(conductor);
            return ResponseEntity.status(HttpStatus.CREATED).body(dtoConverter.convertToDTO(conductor));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Buscar conductores por nombre
    @GetMapping("/search")
    public ResponseEntity<List<ConductorDTO>> listConductores(@RequestParam(required = false) String searchText) {
        List<ConductorDTO> conductores = conductorService.buscarConductorPorNombre(searchText)
                .stream()
                .map(dtoConverter::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(conductores);
    }

    // Eliminar un conductor
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarConductor(@PathVariable Long id) {
        try {
            conductorService.eliminarConductor(id);
            //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Crear un nuevo conductor
    @PostMapping // Cambiado a ResponseEntity<Object>
    public ResponseEntity<Object> crearConductor(
            @Valid @RequestBody ConductorDTO conductorDTO, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        try {
            Conductor conductor = dtoConverter.convertToEntity(conductorDTO);
            conductorService.guardarConductor(conductor);
            return ResponseEntity.status(HttpStatus.CREATED).body(dtoConverter.convertToDTO(conductor));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
