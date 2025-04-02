package controller;

import DataAccess.XMLManager;
import model.*;

import java.util.List;
import java.util.Scanner;

public class UserController {

    public Usuario registrarUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();
        String contrasenaHash = Integer.toHexString(contrasena.hashCode());
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
            System.out.println("Leído del archivo creadores.xml: " + creadores.size() + " elementos.");
            creadores.add(creador);
            XMLManager.writeXML(WrapperCreador.class, creadores, "creadores.xml");
            System.out.println("Escrito en el archivo creadores.xml: " + creadores.size() + " elementos.");
            return creador;
        } else {
            Colaborador colaborador = new Colaborador(nombre, usuario, contrasenaHash, correoElectronico);
            List<Colaborador> colaboradores = XMLManager.readXML(WrapperColaborador.class, Colaborador.class, "colaboradores.xml");
            System.out.println("Leído del archivo colaboradores.xml: " + colaboradores.size() + " elementos.");
            colaboradores.add(colaborador);
            XMLManager.writeXML(WrapperColaborador.class, colaboradores, "colaboradores.xml");
            System.out.println("Escrito en el archivo colaboradores.xml: " + colaboradores.size() + " elementos.");
            return colaborador;
        }
    }

    public boolean iniciarSesion(String usuario, String contrasena) {
        String contrasenaHash = Integer.toHexString(contrasena.hashCode());
        System.out.println("Hash de la contraseña ingresada: " + contrasenaHash);
        return Sesion.getInstance().iniciarSesion(usuario, contrasenaHash);
    }

    public void cerrarSesion() {
        Sesion.getInstance().cerrarSesion();
    }

    public Usuario buscarUsuario(String usuario, String contraseñaHash) {
        List<Creador> creadores = XMLManager.readXML(WrapperCreador.class, Creador.class, "creadores.xml");
        for (Creador creador : creadores) {
            if (creador.getUsuario().equals(usuario) && creador.getContrasenaHash().equals(contraseñaHash)) {
                return creador;
            }
        }

        List<Colaborador> colaboradores = XMLManager.readXML(WrapperColaborador.class, Colaborador.class, "colaboradores.xml");
        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getUsuario().equals(usuario) && colaborador.getContrasenaHash().equals(contraseñaHash)) {
                return colaborador;
            }
        }

        return null;
    }
}