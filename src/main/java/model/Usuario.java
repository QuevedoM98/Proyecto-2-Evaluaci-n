// Usuario.java
package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

@XmlRootElement(name = "Usuario")
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario implements Serializable {
    private String nombre;
    private String usuario;
    private String contrasenaHash;
    private String correoElectronico;
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public Usuario() {} // Constructor vacío necesario para JAXB

    public Usuario(String nombre, String usuario, String contrasena, String correoElectronico) {
        this.nombre = nombre;
        this.usuario = usuario;
        setContrasenaHash(contrasena);
        setCorreoElectronico(correoElectronico);
        usuarios.add(this);
    }

    // Getters y setters...


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(ArrayList<Usuario> usuarios) {
        Usuario.usuarios = usuarios;
    }

    private boolean validarCorreoElectronico(String correo) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(correo).matches();
    }

    private String hashContrasena(String contrasena) {
        return Integer.toHexString(contrasena.hashCode());
    }

    public boolean verificarContrasena(String contrasena) {
        return this.contrasenaHash.equals(hashContrasena(contrasena));
    }
}