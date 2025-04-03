package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@XmlRootElement(name = "actividad")
@XmlAccessorType(XmlAccessType.FIELD)
public class Actividad {

    @XmlElement
    private String nombre;
    @XmlElement
    private String descripcion;
    @XmlElement
    private Date fechaInicio;
    @XmlElement
    private Date fechaFin;
    @XmlElement
    private Estado estado;
    @XmlElementWrapper(name = "voluntariosAsignados")
    @XmlElement(name = "voluntario")
    private List<Colaborador> voluntariosAsignados;
    @XmlElement
    private Creador creador;
    @XmlElement
    private int puntos;

    public Actividad() {
    }

    /**
     * Constructor de la clase Actividad.
     * @param nombre Nombre de la actividad.
     * @param descripcion Descripción de la actividad.
     * @param fechaInicio Fecha de inicio de la actividad.
     * @param fechaFin Fecha de fin de la actividad.
     * @param estado Estado de la actividad.
     * @param voluntariosAsignados Lista de voluntarios asignados a la actividad.
     * @param creador Creador de la actividad.
     */
    public Actividad(String nombre, String descripcion, Date fechaInicio, Date fechaFin, Estado estado, List<Colaborador> voluntariosAsignados, Creador creador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.voluntariosAsignados = voluntariosAsignados != null ? voluntariosAsignados : new ArrayList<>();
        this.creador = creador;
    }

    /**
     * Constructor de la clase Actividad con puntos.
     * @param nombre Nombre de la actividad.
     * @param descripcion Descripción de la actividad.
     * @param fechaInicio Fecha de inicio de la actividad.
     * @param fechaFin Fecha de fin de la actividad.
     * @param estado Estado de la actividad.
     * @param voluntariosAsignados Lista de voluntarios asignados a la actividad.
     * @param creador Creador de la actividad.
     * @param puntos Puntos asignados a la actividad.
     */
    public Actividad(String nombre, String descripcion, Date fechaInicio, Date fechaFin, Estado estado, List<Colaborador> voluntariosAsignados, Creador creador, int puntos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.voluntariosAsignados = voluntariosAsignados != null ? voluntariosAsignados : new ArrayList<>();
        this.creador = creador;
        this.puntos = puntos;
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

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @XmlType(name = "ActividadEstado")
    public enum Estado {
        PENDIENTE,
        EN_PROGRESO,
        COMPLETADA,
        CANCELADA
    }
}
