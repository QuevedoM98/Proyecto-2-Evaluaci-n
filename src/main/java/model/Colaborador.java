package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "colaborador")
@XmlAccessorType(XmlAccessType.FIELD)
public class Colaborador extends Usuario {
    @XmlElement
    private List<Actividad> listaActividades;
    @XmlElement
    private int puntos; // Añadir atributo puntos

    public Colaborador() {
        this.listaActividades = new ArrayList<>();
        this.puntos = 0; // Inicializar puntos
    }

    /**
     * Constructor de la clase Colaborador.
     * @param nombre Nombre del colaborador.
     * @param usuario Nombre de usuario del colaborador.
     * @param contrasenaHash Hash de la contraseña del colaborador.
     * @param correoElectronico Correo electrónico del colaborador.
     */
    public Colaborador(String nombre, String usuario, String contrasenaHash, String correoElectronico) {
        super(nombre, usuario, contrasenaHash, correoElectronico);
        this.listaActividades = new ArrayList<>();
        this.puntos = 0; // Inicializar puntos
    }

    public List<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(List<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public int getPuntos() {
        return puntos;
    }

    /**
     * Agrega puntos al colaborador.
     * @param puntos Puntos a agregar.
     */
    public void agregarPuntos(int puntos) {
        this.puntos += puntos;
    }

}
