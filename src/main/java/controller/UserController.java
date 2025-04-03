package controller;

import DataAccess.XMLManager;
import model.*;

import java.util.List;
import java.util.Scanner;

public class UserController {

    /**
     * Registra un nuevo usuario.
     * @return Usuario registrado.
     */
    public Usuario registrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();
        String contrasenaHash = Usuario.hashContrasena(contrasena);
        System.out.println("Hash de la contraseña registrada: " + contrasenaHash);
        System.out.print("Ingrese su correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("¿Es usted un creador? (s/n): ");
        String esCreador = scanner.nextLine();

        if (esCreador.equalsIgnoreCase("s")) {
            System.out.print("Ingrese el nombre de su ONG: ");
            String nombreONG = scanner.nextLine();
            Creador creador = new Creador(nombre, usuario, contrasenaHash, correoElectronico, nombreONG);
            List<Creador> creadores = XMLManager.readXML(WrapperCreador.class, Creador.class, "creadores.xml");
            creadores.add(creador);
            XMLManager.writeXML(WrapperCreador.class, creadores, "creadores.xml");
            return creador;
        } else {
            Colaborador colaborador = new Colaborador(nombre, usuario, contrasenaHash, correoElectronico);
            List<Colaborador> colaboradores = XMLManager.readXML(WrapperColaborador.class, Colaborador.class, "colaboradores.xml");
            colaboradores.add(colaborador);
            XMLManager.writeXML(WrapperColaborador.class, colaboradores, "colaboradores.xml");
            return colaborador;
        }
    }

    /**
     * Inicia sesión con el usuario y contraseña proporcionados.
     * @param usuario Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @return true si la sesión se inició correctamente, false en caso contrario.
     */
    public boolean iniciarSesion(String usuario, String contrasena) {
        Usuario usuarioEncontrado = Usuario.autenticarUsuario(usuario, contrasena);
        if (usuarioEncontrado != null) {
            Sesion.getInstance().iniciarSesion(usuarioEncontrado.getNombre(), contrasena);
            return true;
        }
        return false;
    }

    /**
     * Cierra la sesión actual.
     */
    public void cerrarSesion() {
        Sesion.getInstance().cerrarSesion();
        System.out.println("Sesión cerrada."); // Mensaje de depuración
    }

    /**
     * Busca un usuario por su nombre de usuario y hash de contraseña.
     * @param usuario Nombre de usuario.
     * @param contrasenaHash Hash de la contraseña.
     * @return Usuario encontrado o null si no se encuentra.
     */
    public Usuario buscarUsuario(String usuario, String contrasenaHash) {
        List<Creador> creadores = XMLManager.readXML(WrapperCreador.class, Creador.class, "creadores.xml");
        for (Creador creador : creadores) {
            if (creador.getUsuario().equals(usuario) && creador.getContrasenaHash().equals(contrasenaHash)) {
                return creador;
            }
        }

        List<Colaborador> colaboradores = XMLManager.readXML(WrapperColaborador.class, Colaborador.class, "colaboradores.xml");
        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getUsuario().equals(usuario) && colaborador.getContrasenaHash().equals(contrasenaHash)) {
                return colaborador;
            }
        }

        return null;
    }

    /**
     * Carga la lista de colaboradores desde el archivo XML.
     * @return Lista de colaboradores.
     */
    public List<Colaborador> cargarColaboradores() {
        return XMLManager.readXML(WrapperColaborador.class, Colaborador.class, "colaboradores.xml");
    }
}
