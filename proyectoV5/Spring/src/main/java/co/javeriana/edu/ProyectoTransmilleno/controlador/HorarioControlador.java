package co.javeriana.edu.ProyectoTransmilleno.controlador;

import co.javeriana.edu.ProyectoTransmilleno.conversion.HorarioDTOConverter;
import co.javeriana.edu.ProyectoTransmilleno.dto.HorarioDTO;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Horario;
import co.javeriana.edu.ProyectoTransmilleno.servicio.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/horarios")
public class HorarioControlador {

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private HorarioDTOConverter horarioDTOConverter;

    @GetMapping("/list")
    public ResponseEntity<List<HorarioDTO>> getAllHorarios() {
        List<HorarioDTO> horarios = horarioService.getAllHorarios().stream()
                .map(horarioDTOConverter::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(horarios);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<HorarioDTO> getHorarioById(@PathVariable Long id) {
        Horario horario = horarioService.getHorarioById(id);
        HorarioDTO horarioDTO = horarioDTOConverter.convertToDTO(horario);
        return ResponseEntity.status(HttpStatus.OK).body(horarioDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<HorarioDTO> saveHorario(@Valid @RequestBody HorarioDTO horarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Horario horario = horarioDTOConverter.convertToEntity(horarioDTO);
        Horario savedHorario = horarioService.createHorario(horario);
        return ResponseEntity.status(HttpStatus.CREATED).body(horarioDTOConverter.convertToDTO(savedHorario));
    }

    @PutMapping("/edit-form/{id}")
    public ResponseEntity<HorarioDTO> updateHorario(@PathVariable Long id, @Valid @RequestBody HorarioDTO horarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Horario horarioDetails = horarioDTOConverter.convertToEntity(horarioDTO);
        Horario updatedHorario = horarioService.updateHorario(id, horarioDetails);
        return ResponseEntity.status(HttpStatus.OK).body(horarioDTOConverter.convertToDTO(updatedHorario));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id) {
        horarioService.deleteHorario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
