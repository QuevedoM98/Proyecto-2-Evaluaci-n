package model;

import DataAccess.XMLManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario implements Serializable {
    @XmlElement
    private String nombre;
    @XmlElement
    private String usuario;
    @XmlElement
    private String contrasenaHash;
    @XmlElement
    private String correoElectronico;

    // Lista estática para almacenar los usuarios
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nombre, String usuario, String contrasenaHash, String correoElectronico) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenaHash = hashContrasena(contrasenaHash);
        this.correoElectronico = correoElectronico;
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
        this.contrasenaHash = hashContrasena(contrasenaHash);
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    // El campo estático no debe ser serializado
    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(ArrayList<Usuario> usuarios) {
        Usuario.usuarios = usuarios;
    }

    // Validación de correo electrónico y hash de contraseñas (sin cambios)

    public boolean verificarContrasena(String contrasena) {
        return this.contrasenaHash.equals(hashContrasena(contrasena));
    }

    public static String hashContrasena(String contrasena) {
        // Algoritmo de hash
        return Integer.toHexString(contrasena.hashCode());
    }
    private Usuario autenticarUsuario(String usuario, String contrasena) {
        List<Creador> creadores = XMLManager.readXML(WrapperCreador.class, Creador.class, "creadores.xml");
        List<Colaborador> colaboradores = XMLManager.readXML(WrapperColaborador.class, Colaborador.class, "colaboradores.xml");

        for (Creador creador : creadores) {
            if (creador.getUsuario().equals(usuario) && creador.verificarContrasena(contrasena)) {
                return creador;
            }
        }

        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getUsuario().equals(usuario) && colaborador.verificarContrasena(contrasena)) {
                return colaborador;
            }
        }

        return null;
    }
    public static boolean validarCorreoElectronico(String correo) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, correo);
    }
}
