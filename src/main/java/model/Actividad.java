package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Actividad implements Serializable {

    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Colaborador voluntario;
    private EstadoActividad estado;
    private Creador creador;

    public Actividad(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Colaborador voluntario, EstadoActividad estado, Creador creador) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.voluntario = voluntario;
        this.estado = estado;
        this.creador = creador;

    }

    public void darPuntos () {

        this.estado = EstadoActividad.COMPLETADA;
        if (voluntario != null) {
            voluntario.incrementarPuntos(100);
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Colaborador getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Colaborador voluntario) {
        this.voluntario = voluntario;
    }

    public EstadoActividad getEstado() {
        return estado;
    }

    public void setEstado(EstadoActividad estado) {
        this.estado = estado;
    }

    public Creador getCreador() {
        return creador;
    }

    public void setCreador(Creador creador) {
        this.creador = creador;
    }
}
