package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Colaborador")
@XmlAccessorType(XmlAccessType.FIELD)
public class Colaborador extends Usuario {

    private int puntos;
    private List<Actividad> actividades;

    public Colaborador(String nombre, String usuario, String contrasena, String correoElectronico) {
        super(nombre, usuario, contrasena, correoElectronico);
        this.puntos = 0;
        this.actividades = new ArrayList<>();
    }

    public int getPuntos() {
        return puntos;
    }

    public void incrementarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void agregarActividad(Actividad actividad) {
        actividades.add(actividad);
    }

    public void eliminarActividad(Actividad actividad) {
        actividades.remove(actividad);
    }
}



