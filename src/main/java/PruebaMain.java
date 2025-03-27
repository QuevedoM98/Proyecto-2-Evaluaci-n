import model.PersistenciaXML;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class PruebaMain {

    private static final String ARCHIVO_XML = "usuarios.xml";

    public static void main(String[] args) {
        // Crear lista de usuarios
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario("Juan", "Juan", "1234", "Juan@gmail.com");
        usuario1.setNombre("Juan");
        Usuario usuario2 = new Usuario("Ana", "Ana", "1234", "ana@gmail.com");
        usuario2.setNombre("Ana");
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        // Guardar usuarios en XML
        PersistenciaXML.guardarDatos(usuarios, Usuario.class, ARCHIVO_XML);

        // Cargar usuarios desde XML
        List<Usuario> usuariosCargados = PersistenciaXML.cargarDatos(Usuario.class, ARCHIVO_XML);

        // Verificar que los usuarios cargados son los mismos que los guardados
        if (usuarios.size() == usuariosCargados.size()) {
            System.out.println("Prueba exitosa: Los usuarios se guardaron y cargaron correctamente.");
        } else {
            System.out.println("Prueba fallida: La lista de usuarios cargados no coincide con la lista guardada.");
        }
    }
}