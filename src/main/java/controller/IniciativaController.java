package controller;

import model.Creador;
import model.Iniciativa;
import model.PersistenciaXML;

import java.util.List;

public class IniciativaController {

    public boolean crearIniciativa(String nombreIniciativa, String descripcion, Creador creador) {
        List<Iniciativa> iniciativas = PersistenciaXML.cargarDatos(Iniciativa.class, "Iniciativas.xml");
        for (Iniciativa i : iniciativas) {
            if (i.getNombreIniciativa().equals(nombreIniciativa)) {
                System.out.println("El nombre de la iniciativa ya está en uso.");
                return false;
            }
        }
        Iniciativa nuevaIniciativa = new Iniciativa(nombreIniciativa, descripcion, creador);
        iniciativas.add(nuevaIniciativa);
        PersistenciaXML.guardarDatos(iniciativas, Iniciativa.class, "Iniciativas.xml");
        return true;
    }

    public List<Iniciativa> listarIniciativas() {
        return PersistenciaXML.cargarDatos(Iniciativa.class, "Iniciativas.xml");
    }
}