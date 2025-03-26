package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Creador extends Usuario {
    private String nombreONG;
    private List<Actividad> actividades;

    public Creador(String nombre, String usuario, String contrasena, String correoElectronico, String nombreONG) {
        super(nombre, usuario, contrasena, correoElectronico);
        this.nombreONG = nombreONG;
        this.actividades = new ArrayList<>();
    }

    public String getNombreONG() {
        return nombreONG;
    }

    public void setNombreONG(String nombreONG) {
        this.nombreONG = nombreONG;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void crearActividad(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de finalización.");
        }
        Actividad actividad = new Actividad(nombre, descripcion, fechaInicio, fechaFin, this);
        actividades.add(actividad);
    }

    public void eliminarActividad(Actividad actividad) {
        actividades.remove(actividad);
    }

    public void actualizarActividad(Actividad actividad, String nuevoNombre, String nuevaDescripcion, LocalDate nuevaFechaInicio, LocalDate nuevaFechaFin) {
        if (nuevaFechaInicio.isAfter(nuevaFechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de finalización.");
        }
        actividad.setNombre(nuevoNombre);
        actividad.setDescripcion(nuevaDescripcion);
        actividad.setFechaInicio(nuevaFechaInicio);
        actividad.setFechaFin(nuevaFechaFin);
    }

    public void asignarVoluntario(Actividad actividad, Colaborador voluntario) {
        actividad.setVoluntario(voluntario);
    }
}


