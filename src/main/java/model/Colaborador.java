package model;

public class Colaborador extends Usuario {

    private int puntos;

    public Colaborador(String nombre, String usuario, String contrasena, String correoElectronico) {
        super(nombre, usuario, contrasena, correoElectronico);
        this.puntos = 0;
    }

    public int getPuntos() {
        return puntos;
    }

    public void incrementarPuntos(int puntos) {
        this.puntos += puntos;
    }
}

}

