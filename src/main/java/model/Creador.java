package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "creador")
@XmlAccessorType(XmlAccessType.FIELD)
public class Creador extends Usuario {
    @XmlElement
    private String nombreONG;
    @XmlElement
    private List<Iniciativa> iniciativas;

    public Creador() {
    }

    public Creador(String nombre, String usuario, String contrasenaHash, String correoElectronico, String nombreONG) {
        super(nombre, usuario, contrasenaHash, correoElectronico);
        this.nombreONG = nombreONG;
    }

    public String getNombreONG() {
        return nombreONG;
    }

    public void setNombreONG(String nombreONG) {
        this.nombreONG = nombreONG;
    }

    public List<Iniciativa> getIniciativas() {
        return iniciativas;
    }

    public void setIniciativas(List<Iniciativa> iniciativas) {
        this.iniciativas = iniciativas;
    }
}
