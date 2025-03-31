import controller.UserController;
import controller.IniciativaController;
import model.Creador;
import model.Sesion;
import utils.Utils;

public class PruebaMain {

    public static void main(String[] args) {
        UserController userController = new UserController();
        IniciativaController iniciativaController = new IniciativaController();

        while (true) {
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Crear Iniciativa");
            System.out.println("4. Listar Iniciativas");
            System.out.println("5. Cerrar Sesión");
            System.out.println("6. Salir");

            int opcion = Utils.leeEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    String nombre = Utils.pideString("Nombre: ");
                    String usuario = Utils.pideString("Usuario: ");
                    String contrasena = Utils.pideString("Contraseña: ");
                    String correo = Utils.pideString("Correo Electrónico: ");
                    String tipoUsuario = Utils.pideString("Tipo de Usuario (Creador/Colaborador): ");
                    userController.registrarUsuario(nombre, usuario, contrasena, correo, tipoUsuario);
                    break;
                case 2:
                    usuario = Utils.pideString("Usuario: ");
                    contrasena = Utils.pideString("Contraseña: ");
                    userController.iniciarSesion(usuario, contrasena);
                    break;
                case 3:
                    if (Sesion.getInstance().getUsuarioActual() instanceof Creador) {
                        String nombreIniciativa = Utils.pideString("Nombre de la Iniciativa: ");
                        String descripcion = Utils.pideString("Descripción: ");
                        Creador creador = (Creador) Sesion.getInstance().getUsuarioActual();
                        iniciativaController.crearIniciativa(nombreIniciativa, descripcion, creador);
                    } else {
                        System.out.println("Solo los creadores pueden crear iniciativas.");
                    }
                    break;
                case 4:
                    iniciativaController.listarIniciativas().forEach(i -> {
                        System.out.println("Nombre: " + i.getNombreIniciativa());
                        System.out.println("Descripción: " + i.getDescripcion());
                        System.out.println("Creador: " + i.getCreador().getNombre());
                    });
                    break;
                case 5:
                    userController.cerrarSesion();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}