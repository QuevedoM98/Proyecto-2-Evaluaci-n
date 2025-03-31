package model;

import model.PersistenciaXML;
import model.Usuario;

import java.util.List;

public class Sesion {
    private static Sesion instancia;
    private Usuario usuarioActual;

    private Sesion() {} // Constructor privado

    public static Sesion getInstance() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        boolean iniciado = false;
        if (usuarioActual == null) {
            List<Usuario> usuarios = PersistenciaXML.cargarDatos(Usuario.class, "Usuarios.xml");
            for (Usuario usuario : usuarios) {
                if (usuario.getUsuario().equals(nombreUsuario) && usuario.verificarContrasena(contrasena)) {
                    this.usuarioActual = usuario;
                    System.out.println("Sesión iniciada para " + usuario.getNombre());
                    iniciado = true;
                    break;
                }
            }
            if (!iniciado) {
                System.out.println("Usuario o contraseña incorrectos.");
            }
        } else {
            System.out.println("Ya hay una sesión activa.");
        }
        return iniciado;
    }

    public void cerrarSesion() {
        if (usuarioActual != null) {
            System.out.println("Sesión cerrada para " + usuarioActual.getNombre());
            usuarioActual = null;
        } else {
            System.out.println("No hay sesión activa.");
        }
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
