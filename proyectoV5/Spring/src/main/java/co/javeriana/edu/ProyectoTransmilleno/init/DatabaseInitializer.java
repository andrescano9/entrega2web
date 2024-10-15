package co.javeriana.edu.ProyectoTransmilleno.init;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.javeriana.edu.ProyectoTransmilleno.modelo.Conductor;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Bus;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Asignacion;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Estacion;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Ruta;
import co.javeriana.edu.ProyectoTransmilleno.modelo.Horario;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.AsignacionRepositorio;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.BusRepositorio;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.ConductorRepositorio;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.EstacionRepositorio;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.HorarioRepositorio;
import co.javeriana.edu.ProyectoTransmilleno.repositorio.RutaRepositorio;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private AsignacionRepositorio asignacionRepositorio;

    @Autowired
    private BusRepositorio busRepositorio;

    @Autowired
    private ConductorRepositorio conductorRepositorio;

    @Autowired
    private HorarioRepositorio horarioRepositorio;

    @Autowired
    private RutaRepositorio rutaRepositorio;

    @Autowired
    private EstacionRepositorio estacionRepositorio;

    @Override
    public void run(String... args) throws Exception {

        Conductor conductor1 = new Conductor("Hector Malao", 123456, 310494939, "1600 Pennsylvania Avenue NW, Washington, D.C. 20500, United States");
        Conductor conductor2 = new Conductor("Pablo Meruda", 654321, 30250302, "221B Baker Street, London, NW1 6XE, United Kingdom");
        Conductor conductor3 = new Conductor("Juana Varon", 42367894, 31045678, "1 Infinite Loop, Cupertino, CA 95014, USA");

        Bus bus1 = new Bus("ABX-982", "Volvo B340M");
        Bus bus2 = new Bus("CFT-746", "Mercedes-Benz O500");
        Bus bus3 = new Bus("ZKP-319", "Scania K310");
        Bus bus4 = new Bus("LZM-842", "MAN Lion's City");

        // Guardar estaciones primero
        Estacion estacion1 = estacionRepositorio.save(new Estacion("Estación El Pinar"));
        Estacion estacion2 = estacionRepositorio.save(new Estacion("Estación La Sabana"));
        Estacion estacion3 = estacionRepositorio.save(new Estacion("Estación Mirador Central"));
        Estacion estacion4 = estacionRepositorio.save(new Estacion("Estación Las Colinas"));
        Estacion estacion5 = estacionRepositorio.save(new Estacion("Estación San Rafael"));

        Horario horario1 = new Horario("7AM", "9AM", Arrays.asList("Lunes", "Martes"));
        Horario horario2 = new Horario("9AM", "11AM", Arrays.asList("Lunes"));
        Horario horario3 = new Horario("7PM", "9PM", Arrays.asList("Lunes", "Miercoles"));
        Horario horario4 = new Horario("9PM", "11PM", Arrays.asList("Lunes"));

        // Crear rutas
        Ruta ruta1 = new Ruta("Ruta Express Norte-Sur");
        Ruta ruta2 = new Ruta("Ruta Central Metropolitana");
        Ruta ruta3 = new Ruta("Ruta EcoTurística Montaña");
        Ruta ruta4 = new Ruta("Ruta Interurbana del Sol");

        /*// Asociar estaciones a rutas
        ruta1.addEstacion(estacion1);
        ruta1.addEstacion(estacion2);  
        ruta2.addEstacion(estacion3);
        ruta2.addEstacion(estacion4);
        ruta3.addEstacion(estacion5); */

        // Guardar conductores, buses y rutas
        conductorRepositorio.save(conductor1);
        conductorRepositorio.save(conductor2);
        conductorRepositorio.save(conductor3);

        busRepositorio.save(bus1);
        busRepositorio.save(bus2);
        busRepositorio.save(bus3);
        busRepositorio.save(bus4);

        horarioRepositorio.save(horario1);
        horarioRepositorio.save(horario2);
        horarioRepositorio.save(horario3);
        horarioRepositorio.save(horario4);

        rutaRepositorio.save(ruta1);
        rutaRepositorio.save(ruta2);
        rutaRepositorio.save(ruta3);
        rutaRepositorio.save(ruta4);

        // Crear asignación
        Asignacion asignacion = new Asignacion();
        asignacion.asociarConductor(conductor1);
        asignacion.asociarBus(bus1);
        asignacion.asociarHorario(horario1);
        asignacion.asociarRuta(ruta1);

        // Guardar la asignación
        asignacionRepositorio.save(asignacion);

        System.out.println("Asignación creada: " + asignacion.getId());
    }
}
