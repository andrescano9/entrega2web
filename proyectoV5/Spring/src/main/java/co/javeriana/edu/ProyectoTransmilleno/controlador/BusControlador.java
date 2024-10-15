package co.javeriana.edu.ProyectoTransmilleno.controlador;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import co.javeriana.edu.ProyectoTransmilleno.dto.BusDTO;
import co.javeriana.edu.ProyectoTransmilleno.conversion.BusDTOConverter;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Bus;
import co.javeriana.edu.ProyectoTransmilleno.servicio.BusService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bus")
public class BusControlador {

    @Autowired
    private BusService busService;

    @Autowired
    private BusDTOConverter dtoConverter;

    // Listar todos los buses
    @GetMapping("/list")
    public ResponseEntity<List<BusDTO>> listarBuses() {
        List<BusDTO> buses = busService.listarBuses()
                .stream()
                .map(dtoConverter::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(buses);
    }

    // Recuperar un bus por ID
    @GetMapping("/view/{idBus}")
    public ResponseEntity<BusDTO> recuperarBus(@PathVariable Long idBus) {
        Bus bus = busService.recuperarBus(idBus);
        BusDTO busDTO = dtoConverter.convertToDTO(bus);
        return ResponseEntity.status(HttpStatus.OK).body(busDTO);
    }

    // Crear un nuevo bus
    @PostMapping
    public ResponseEntity<Object> crearBus(@Valid @RequestBody BusDTO busDTO, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        try {
            // Manejar el caso cuando el id es 0, lo tratamos como un nuevo bus
            if (busDTO.getId() == 0) {
                busDTO.setId(null); // Hibernate generará el id automáticamente
            }

            Bus bus = dtoConverter.convertToEntity(busDTO);
            busService.guardarBus(bus);
            return ResponseEntity.status(HttpStatus.CREATED).body(dtoConverter.convertToDTO(bus));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    // Actualizar un bus
    @PutMapping("/edit/{idBus}")
    public ResponseEntity<Object> actualizarBus(@PathVariable Long idBus, @Valid @RequestBody BusDTO busDTO,
                                                BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        try {
            // Verificar si el bus existe
            Bus busExistente = busService.recuperarBus(idBus);

            if (busExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            // Convertir DTO a entidad y guardar
            Bus bus = dtoConverter.convertToEntity(busDTO);
            bus.setId(idBus);
            busService.guardarBus(bus);

            return ResponseEntity.status(HttpStatus.OK).body(dtoConverter.convertToDTO(bus));

        } catch (IllegalArgumentException e) {
            System.err.println("Error al actualizar bus: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }

    }

    // Eliminar un bus
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarBus(@PathVariable Long id) {
        try {
            busService.eliminarBus(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Buscar buses por placa
    @GetMapping("/search")
    public ResponseEntity<List<BusDTO>> buscarBuses(@RequestParam(required = false) String searchText) {
        List<BusDTO> buses = busService.buscarBusPorPlaca(searchText)
                .stream()
                .map(dtoConverter::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(buses);
    }
}
