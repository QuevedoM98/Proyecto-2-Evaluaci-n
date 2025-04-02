import controller.UserController;
import model.Colaborador;
import model.Creador;
import model.Usuario;
import view.InicioRegistro;
import view.MenuColaborador;
import view.MenuCreador;

public class Main {
    public static void main(String[] args) {
        InicioRegistro inicioRegistro = new InicioRegistro();
        Usuario usuario = inicioRegistro.mostrarMenu();

        if (usuario != null) {
            if (usuario instanceof Creador) {
                new MenuCreador((Creador) usuario).mostrarMenu();
            } else if (usuario instanceof Colaborador) {
                new MenuColaborador((Colaborador) usuario).mostrarMenu();
            }
            // Cerrar sesión después de salir del menú
            new UserController().cerrarSesion();
            System.out.println("Sesión cerrada."); // Mensaje de depuración
        }
    }
}
