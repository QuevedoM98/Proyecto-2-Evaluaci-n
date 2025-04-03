package model;

import DataAccess.XMLManager;

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
            List<Creador> creadores = XMLManager.readXML(WrapperCreador.class, Creador.class, "creadores.xml");
            List<Colaborador> colaboradores = XMLManager.readXML(WrapperColaborador.class, Colaborador.class, "colaboradores.xml");

            for (Creador creador : creadores) {
                if (verificarCredenciales(creador, nombreUsuario, contrasena)) {
                    this.usuarioActual = creador;
                    System.out.println("Sesión iniciada para " + creador.getNombre());
                    iniciado = true;
                    break;
                }
            }

            if (!iniciado) {
                for (Colaborador colaborador : colaboradores) {
                    if (verificarCredenciales(colaborador, nombreUsuario, contrasena)) {
                        this.usuarioActual = colaborador;
                        System.out.println("Sesión iniciada para " + colaborador.getNombre());
                        iniciado = true;
                        break;
                    }
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

    private boolean verificarCredenciales(Usuario usuario, String nombreUsuario, String contrasena) {
        return usuario.getUsuario().equals(nombreUsuario) && usuario.verificarContrasena(contrasena);
    }

    public boolean haySesionActiva() {
        return usuarioActual != null;
    }

    public void cerrarSesion() {
        if (usuarioActual != null) {
            System.out.println("Sesión cerrada para " + usuarioActual.getNombre());
            usuarioActual = null;
        } else {
            System.out.println("No hay sesión activa.");
        }
    }

    public boolean esCreador() {
        return usuarioActual instanceof Creador;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
