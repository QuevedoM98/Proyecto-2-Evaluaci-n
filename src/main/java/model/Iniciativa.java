package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Iniciativa implements Serializable {
    private String nombre;
    private String descripcion;
    private String creador;
    private static List<Iniciativa> iniciativas = new ArrayList<>();

    public Iniciativa(String nombre, String descripcion, String creador) {
        if (nombreEnUso(nombre)) {
            throw new IllegalArgumentException("El nombre ya está en uso.");
        }
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        iniciativas.add(this);
    }

    public String getNombreIniciativa() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCreador() {
        return creador;
    }

    public static List<Iniciativa> getIniciativas() {
        return iniciativas;
    }

    private static boolean nombreEnUso(String nombre) {
        for (Iniciativa i : iniciativas) {
            if (i.getNombreIniciativa().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
}
