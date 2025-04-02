package view;
import DataAccess.XMLManager;
import model.*;
import controller.UserController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InicioRegistro {
    private Scanner scanner = new Scanner(System.in);
    private UserController userController = new UserController();

    public Usuario mostrarMenu() {
        while (true) {
            System.out.println("1- Iniciar Sesión");
            System.out.println("2- Registrarse");
            System.out.println("3- Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    Usuario usuario = iniciarSesion();
                    if (usuario != null) {
                        userController.iniciarSesion(usuario.getUsuario(), usuario.getContrasenaHash());
                    }
                    return usuario;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return null;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    private Usuario iniciarSesion() {
        System.out.println("Ingrese su nombre de usuario:");
        String usuario = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contrasena = scanner.nextLine();
        String contrasenaHash = Usuario.hashContrasena(contrasena); // Generar hash de la contraseña aquí
        System.out.println("Hash de la contraseña ingresada: " + contrasenaHash); // Mensaje de depuración
        Usuario usuarioEncontrado = userController.buscarUsuario(usuario, contrasenaHash);
        if (usuarioEncontrado != null) {
            System.out.println("Inicio de sesión exitoso.");
            return usuarioEncontrado;
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
            return null;
        }
    }

    private void registrarUsuario() {
        userController.registrarUsuario();
    }

    private String generarHash(String contrasena) {
        return Integer.toHexString(contrasena.hashCode());
    }
}
