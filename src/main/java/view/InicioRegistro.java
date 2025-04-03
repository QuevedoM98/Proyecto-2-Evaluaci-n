package view;

import controller.UserController;
import model.Usuario;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InicioRegistro {
    private Scanner scanner = new Scanner(System.in);
    private UserController userController = new UserController();

    public Usuario mostrarMenu() {
        while (true) {
            System.out.println("\n==============================");
            System.out.println("     🌠 CONTRIBUYA SIN LÍMITES 🌠");
            System.out.println("==============================");
            System.out.println("       Menú de Inicio");
            System.out.println("==============================");
            System.out.println("1- Iniciar Sesión");
            System.out.println("2- Registrarse");
            System.out.println("3- Salir");
            System.out.println("==============================");
            System.out.print("Seleccione una opción: ");
            try {
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
            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    private Usuario iniciarSesion() {
        System.out.println("Ingrese su nombre de usuario:");
        String usuario = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contrasena = scanner.nextLine();
        String contrasenaHash = generarHashSHA256(contrasena);
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

    public void cerrarSesion() {
        System.out.println("Cerrando sesión...");
        userController.cerrarSesion();
    }

    private String generarHashSHA256(String contrasena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(contrasena.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar el hash SHA-256", e);
        }
    }
}
