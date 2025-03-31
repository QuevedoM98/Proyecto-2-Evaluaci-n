package controller;

import model.PersistenciaXML;
import model.Usuario;
import model.Creador;
import model.Colaborador;
import model.Sesion;

import java.util.List;

public class UserController {

    public boolean registrarUsuario(String nombre, String usuario, String contrasena, String correoElectronico, String tipoUsuario) {
        List<Usuario> usuarios = PersistenciaXML.cargarDatos(Usuario.class, "Usuarios.xml");
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                System.out.println("El nombre de usuario ya está en uso.");
                return false;
            }
        }
        Usuario nuevoUsuario;
        if (tipoUsuario.equals("Creador")) {
            nuevoUsuario = new Creador(nombre, usuario, contrasena, correoElectronico, "Nombre ONG");
            PersistenciaXML.guardarDatos((List<Creador>) (List<?>) usuarios, Creador.class, "Creadores.xml");
        } else {
            nuevoUsuario = new Colaborador(nombre, usuario, contrasena, correoElectronico);
            PersistenciaXML.guardarDatos((List<Colaborador>) (List<?>) usuarios, Colaborador.class, "Colaboradores.xml");
        }
        usuarios.add(nuevoUsuario);
        PersistenciaXML.guardarDatos(usuarios, Usuario.class, "Usuarios.xml");
        return true;
    }

    public boolean iniciarSesion(String usuario, String contrasena) {
        return Sesion.getInstance().iniciarSesion(usuario, contrasena);
    }

    public void cerrarSesion() {
        Sesion.getInstance().cerrarSesion();
    }
}