package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "iniciativa")
@XmlAccessorType(XmlAccessType.FIELD)
public class Iniciativa {
    @XmlElement
    private String nombreIniciativa;
    @XmlElement
    private String descripcion;
    @XmlElement
    private Creador creador;
    @XmlElement
    private List<Actividad> actividades;
    @XmlElement
    private Date fechaInicio;
    @XmlElement
    private Date fechaFin;
    @XmlElement
    private Estado estado;

    public Iniciativa() {
    }

    /**
     * Constructor de la clase Iniciativa.
     * @param nombreIniciativa Nombre de la iniciativa.
     * @param descripcion Descripción de la iniciativa.
     * @param creador Creador de la iniciativa.
     * @param fechaInicio Fecha de inicio de la iniciativa.
     * @param fechaFin Fecha de fin de la iniciativa.
     * @param estado Estado de la iniciativa.
     */
    public Iniciativa(String nombreIniciativa, String descripcion, Creador creador, Date fechaInicio, Date fechaFin, Estado estado) {
        this.nombreIniciativa = nombreIniciativa;
        this.descripcion = descripcion;
        this.creador = creador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.actividades = new ArrayList<>();
    }

    public String getNombreIniciativa() {
        return nombreIniciativa;
    }

    public void setNombreIniciativa(String nombreIniciativa) {
        this.nombreIniciativa = nombreIniciativa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Creador getCreador() {
        return creador;
    }

    public void setCreador(Creador creador) {
        this.creador = creador;
    }

    public List<Actividad> getActividades() {
        if (actividades == null) {
            actividades = new ArrayList<>();
        }
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    /**
     * Agrega una actividad a la lista de actividades de la iniciativa.
     * @param actividad Actividad a agregar.
     */
    public void agregarActividad(Actividad actividad) {
        getActividades().add(actividad);
    }

    /**
     * Elimina una actividad de la lista de actividades de la iniciativa.
     * @param nombre Nombre de la actividad a eliminar.
     */
    public void eliminarActividad(String nombre) {
        getActividades().removeIf(actividad -> actividad.getNombre().equalsIgnoreCase(nombre));
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

    @XmlType(name = "IniciativaEstado")
    public enum Estado {
        PENDIENTE,
        EN_PROGRESO,
        COMPLETADA,
        CANCELADA
    }
}