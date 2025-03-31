package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Colaborador")
@XmlAccessorType(XmlAccessType.FIELD)
public class Iniciativa implements Serializable{
    private String nombreIniciativa;
    private String descripcion;
    private Creador creador;

    public Iniciativa(String nombreIniciativa, String descripcion, Creador creador) {
        this.nombreIniciativa = nombreIniciativa;
        this.descripcion = descripcion;
        this.creador = creador;
    }

    public String getNombreIniciativa() {
        return nombreIniciativa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Creador getCreador() {
        return creador;
    }

}