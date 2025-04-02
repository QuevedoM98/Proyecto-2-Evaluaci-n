package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "iniciativa")
@XmlAccessorType(XmlAccessType.FIELD)
public class Iniciativa {
    @XmlElement
    private String nombreIniciativa;
    @XmlElement
    private String descripcion;
    @XmlElement
    private Creador creador;

    public Iniciativa() {
    }

    public Iniciativa(String nombreIniciativa, String descripcion, Creador creador) {
        this.nombreIniciativa = nombreIniciativa;
        this.descripcion = descripcion;
        this.creador = creador;
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
}