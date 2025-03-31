package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Actividad implements Serializable {
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Creador creador;
    private Colaborador voluntario;

    public Actividad(String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, Creador creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.creador = creador;
    }

    // Getters y setters
}