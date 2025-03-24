import model.Usuario;

public class Sesion {
    private static Sesion instancia;
    private Usuario usuarioActual;

    private Sesion() {} // Constructor privado

    public static Sesion getInstance() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    public boolean iniciarSesion(Usuario usuario) {
        if (usuarioActual == null) {
            this.usuarioActual = usuario;
            System.out.println("Sesión iniciada para " + usuario.getNombre());
            return true;
        } else {
            System.out.println("Ya hay una sesión activa.");
            return false;
        }
    }

    public void cerrarSesion() {
        if (usuarioActual != null) {
            System.out.println("Sesión cerrada para " + usuarioActual.getNombre());
            usuarioActual = null;
        } else {
            System.out.println("No hay sesión activa.");
        }
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
