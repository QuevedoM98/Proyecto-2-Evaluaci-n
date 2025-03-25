import model.PersistenciaXML;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class PruebaMain {

    public static void main(String[] args) {
        // Crear lista de usuarios
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        // Guardar usuarios en XML
        PersistenciaXML.guardarUsuarios(usuarios);

        // Cargar usuarios desde XML
        List<Usuario> usuariosCargados = PersistenciaXML.cargarUsuarios();

        // Verificar que los usuarios cargados son los mismos que los guardados
        if (usuarios.size() == usuariosCargados.size()) {
            System.out.println("Prueba exitosa: Los usuarios se guardaron y cargaron correctamente.");
        } else {
            System.out.println("Prueba fallida: La lista de usuarios cargados no coincide con la lista guardada.");
        }
    }
}