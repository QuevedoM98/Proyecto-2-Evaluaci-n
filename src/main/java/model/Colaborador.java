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

    public Colaborador() {
        this.listaActividades = new ArrayList<>();

    }

    public Colaborador(String nombre, String usuario, String contrasenaHash, String correoElectronico) {
        super(nombre, usuario, contrasenaHash, correoElectronico);
        this.listaActividades = new ArrayList<>();
    }

    public List<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(List<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }
}
