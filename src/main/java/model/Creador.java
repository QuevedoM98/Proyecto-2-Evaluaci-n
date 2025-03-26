package model;


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

    public void crearActividad(String nombre) {
        actividades.add(new Actividad(nombre));
    }

    public void eliminarActividad(Actividad actividad) {
        actividades.remove(actividad);
    }

    public void actualizarActividad(Actividad actividad, String nuevoNombre) {
        actividad.setNombre(nuevoNombre);
    }

    public void asignarVoluntario(Actividad actividad, Colaborador voluntario) {
        actividad.asignarVoluntario(voluntario);
    }

