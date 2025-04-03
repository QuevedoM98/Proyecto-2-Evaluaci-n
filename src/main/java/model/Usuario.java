package model;

import DataAccess.XMLManager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String usuario;
    private String contrasenaHash;
    private String correoElectronico;

    // Constructores, getters y setters...

    public Usuario() {
    }

    public Usuario(String nombre, String usuario, String contrasenaHash, String correoElectronico) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenaHash = contrasenaHash;
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenaHash() {
        return contrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        this.contrasenaHash = contrasenaHash;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Verifica si la contraseña proporcionada coincide con el hash almacenado.
     * @param contrasena Contraseña a verificar.
     * @return true si la contraseña coincide, false en caso contrario.
     */
    public boolean verificarContrasena(String contrasena) {
        return this.contrasenaHash.equals(hashContrasena(contrasena));
    }

    /**
     * Genera el hash de una contraseña utilizando SHA-256.
     * @param contrasena Contraseña a hashear.
     * @return Hash de la contraseña.
     */
    public static String hashContrasena(String contrasena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(contrasena.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Autentica un usuario con su nombre de usuario y contraseña.
     * @param usuario Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @return Usuario autenticado o null si la autenticación falla.
     */
    public static Usuario autenticarUsuario(String usuario, String contrasena) {
        String contrasenaHash = hashContrasena(contrasena);
        List<Creador> creadores = XMLManager.readXML(WrapperCreador.class, Creador.class, "creadores.xml");
        List<Colaborador> colaboradores = XMLManager.readXML(WrapperColaborador.class, Colaborador.class, "colaboradores.xml");

        for (Creador creador : creadores) {
            if (creador.getUsuario().equals(usuario) && creador.verificarContrasena(contrasena)) {
                return creador;
            }
        }

        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getUsuario().equals(usuario) && colaborador.verificarContrasena(contrasena)) {
                return colaborador;
            }
        }

        return null;
    }

    /**
     * Obtiene la lista de iniciativas creadas por el usuario.
     * @return Array de iniciativas creadas por el usuario.
     */
    public Iniciativa[] getListaIniciativas() {
        List<Iniciativa> todasIniciativas = XMLManager.readXML(WrapperIniciativa.class, Iniciativa.class, "iniciativas.xml");
        List<Iniciativa> iniciativasUsuario = new ArrayList<>();
        for (Iniciativa iniciativa : todasIniciativas) {
            if (iniciativa.getCreador().getUsuario().equals(this.usuario)) {
                iniciativasUsuario.add(iniciativa);
            }
        }
        return iniciativasUsuario.toArray(new Iniciativa[0]);
    }
}
