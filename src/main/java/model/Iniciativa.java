// src/main/java/model/Iniciativa.java
package model;

public class Iniciativa {
    private String nombreIniciativa;
    private String descripcion;
    private Creador creador;

    public Iniciativa(String nombreIniciativa, String descripcion, Creador creador) {
        this.nombreIniciativa = nombreIniciativa;
        this.descripcion = descripcion;
        this.creador = creador;
    }

    public String getNombreIniciativa() {
        return nombreIniciativa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Creador getCreador() {
        return creador;
    }

}