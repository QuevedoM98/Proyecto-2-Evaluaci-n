package model;

import exceptions.ContrasenaIncorrectaException;
import exceptions.UsuarioNoEncontradoException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

@XmlRootElement(name = "Actividad")
@XmlAccessorType(XmlAccessType.FIELD)

public class Usuario {
    private String nombre;
    private String usuario;
    private String contrasenaHash;
    private String correoElectronico;
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public Usuario(String nombre, String usuario, String contrasena, String correoElectronico) {
        this.nombre = nombre;
        this.usuario = usuario;
        setContrasena(contrasena);
        setCorreoElectronico(correoElectronico);
        usuarios.add(this);
        if (!this.usuario.equals(usuario)) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado.");
        }
        if (!this.contrasenaHash.equals(contrasena)) {
            throw new ContrasenaIncorrectaException("Contraseña incorrecta.");
        }
    }

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

    public void setContrasena(String contrasena) {
        this.contrasenaHash = hashContrasena(contrasena);
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        if (validarCorreoElectronico(correoElectronico)) {
            this.correoElectronico = correoElectronico;
        } else {
            throw new IllegalArgumentException("Correo electrónico no válido");
        }
    }

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    private boolean validarCorreoElectronico(String correo) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(correo).matches();
    }

    private String hashContrasena(String contrasena) {
        return Integer.toHexString(contrasena.hashCode());
    }

    private boolean usuarioExiste(String usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return true;
            }
        }
        return false;


    }

    public boolean verificarContrasena(String contrasena) {
        return this.contrasenaHash.equals(hashContrasena(contrasena));
    }
}


