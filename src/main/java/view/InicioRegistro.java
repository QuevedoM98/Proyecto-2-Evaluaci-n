package view;
import model.Usuario;
import model.Creador;
import model.Colaborador;
import java.util.Scanner;

public class InicioRegistro {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        while (true) {
            System.out.println("1- Iniciar Sesión");
            System.out.println("2- Registrarse");
            System.out.println("3- Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
    private void iniciarSesion() {
        System.out.println("Ingrese su nombre de usuario:");
        String usuario = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contraseña = scanner.nextLine();
        Usuario user = autenticarUsuario(usuario, contraseña);
        if (user != null) {
            if (user instanceof Creador) {
                mostrarMenuCreador((Creador) user);
            } else if (user instanceof Colaborador) {
                mostrarMenuColaborador((Colaborador) user);
            } else {
                System.out.println("Tipo de usuario no válido.");
            }
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }
    private Usuario autenticarUsuario(String usuario, String contrasena) {
        for (Usuario u : Usuario.getUsuarios()) {
            if (u.getUsuario().equals(usuario) && u.getContrasenaHash().equals(Integer.toHexString(contrasena.hashCode()))) {
                return u;
            }
        }
        return null;
    }
    private void registrarUsuario() {
        System.out.println("Registro de nuevo usuario:");
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese su nombre de usuario:");
        String usuario = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contrasena = scanner.nextLine();
        String correoElectronico = scanner.nextLine();
        System.out.println("¿Será usted Creador o Colaborador? (Creador/Colaborador)");
        String tipoUsuario = scanner.nextLine();

        if (tipoUsuario.equalsIgnoreCase("Creador")) {
            System.out.println("Ingrese el nombre de la ONG:");
            String nombreONG = scanner.nextLine();
            new Creador(nombre, usuario, contrasena, correoElectronico, nombreONG);
        } else if (tipoUsuario.equalsIgnoreCase("Colaborador")) {
            new Colaborador(nombre, usuario, contrasena, correoElectronico);
        } else {
            System.out.println("Tipo de usuario no válido.");
        }
        System.out.println("Usuario registrado exitosamente.");
    }
    private void mostrarMenuCreador(Creador creador) {
        MenuCreador menuCreador = new MenuCreador(creador);
        menuCreador.mostrarMenu();
    }
    private void mostrarMenuColaborador(Colaborador colaborador) {
        MenuColaborador menuColaborador = new MenuColaborador(colaborador);
        menuColaborador.mostrarMenu();
    }
}