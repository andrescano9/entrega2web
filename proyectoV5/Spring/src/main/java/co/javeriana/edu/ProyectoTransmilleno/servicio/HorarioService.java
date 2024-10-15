package co.javeriana.edu.ProyectoTransmilleno.servicio;

import co.javeriana.edu.ProyectoTransmilleno.modelo.Horario;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.HorarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepositorio horarioRepositorio;

    public List<Horario> getAllHorarios() {
        return horarioRepositorio.findAll();
    }

    public Horario createHorario(Horario horario) {
        return horarioRepositorio.save(horario);
    }

    public Horario updateHorario(Long id, Horario horarioDetails) {
        Horario horario = horarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado con ID: " + id));

        // Actualiza los campos necesarios
        horario.setHoraInicio(horarioDetails.getHoraInicio());
        horario.setHoraFin(horarioDetails.getHoraFin());
        horario.setDiaSemana(horarioDetails.getDiaSemana()); // Asegúrate de que también quieras actualizar esto

        return horarioRepositorio.save(horario);
    }


    public void deleteHorario(Long id) {
        Horario horario = horarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado con ID: " + id));
        horarioRepositorio.delete(horario);
    }

    public Horario getHorarioById(Long id) {
        return horarioRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Horario no encontrado con ID: " + id));
}

}
