package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "actividades") // Representa la raíz del XML
@XmlAccessorType(XmlAccessType.FIELD)
public class Actividad {

    @XmlElement(name = "actividad") // Cada elemento dentro de "actividades" es una "actividad"
    private List<Actividad> actividades;

    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private Estado estado;
    private List<Colaborador> voluntariosAsignados;
    private Creador creador;

    public Actividad() {
        // Constructor por defecto sin argumentos
    }

    public Actividad(String nombre, String descripcion, Date fechaInicio, Date fechaFin, Estado estado, List<Colaborador> voluntariosAsignados, Creador creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.voluntariosAsignados = voluntariosAsignados;
        this.creador = creador;
    }

    // Getters y Setters
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Colaborador> getVoluntariosAsignados() {
        return voluntariosAsignados;
    }

    public void setVoluntariosAsignados(List<Colaborador> voluntariosAsignados) {
        this.voluntariosAsignados = voluntariosAsignados;
    }

    public Creador getCreador() {
        return creador;
    }

    public void setCreador(Creador creador) {
        this.creador = creador;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public enum Estado {
        PENDIENTE,
        EN_PROGRESO,
        COMPLETADA,
        CANCELADA
    }
}
