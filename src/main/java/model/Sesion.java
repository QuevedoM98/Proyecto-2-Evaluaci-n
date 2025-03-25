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
        boolean iniciado = false;
        if (usuarioActual == null) {
            this.usuarioActual = usuario;
            System.out.println("Sesión iniciada para " + usuario.getNombre());
            iniciado = true;
        } else {
            System.out.println("Ya hay una sesión activa.");
        }
        return iniciado;
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
