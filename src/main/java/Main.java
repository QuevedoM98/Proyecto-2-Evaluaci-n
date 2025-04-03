import model.Colaborador;
import model.Creador;
import model.Usuario;
import view.InicioRegistro;
import view.MenuColaborador;
import view.MenuCreador;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de InicioRegistro
        InicioRegistro inicioRegistro = new InicioRegistro();
        Usuario usuario;
        do {
            // Mostrar el menú de inicio de sesión y obtener el usuario
            usuario = inicioRegistro.mostrarMenu();
            if (usuario != null) {
                // Verificar el tipo de usuario y mostrar el menú correspondiente
                if (usuario instanceof Creador) {
                    new MenuCreador((Creador) usuario).mostrarMenu();
                } else if (usuario instanceof Colaborador) {
                    new MenuColaborador((Colaborador) usuario).mostrarMenu();
                }
                // Cerrar sesión después de salir del menú
                inicioRegistro.cerrarSesion();
            }
        } while (usuario != null);
    }
}
