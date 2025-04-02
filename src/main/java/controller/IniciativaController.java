package controller;

import DataAccess.XMLManager;
import model.Iniciativa;
import model.WrapperIniciativa;
import model.Creador;

import java.util.List;

public class IniciativaController {

    public void crearIniciativa(String nombreIniciativa, String descripcion, Creador creador) {
        List<Iniciativa> iniciativas = XMLManager.readXML(WrapperIniciativa.class, Iniciativa.class, "Iniciativas.xml");
        Iniciativa nuevaIniciativa = new Iniciativa(nombreIniciativa, descripcion, creador);
        iniciativas.add(nuevaIniciativa);
        XMLManager.writeXML(WrapperIniciativa.class, iniciativas, "Iniciativas.xml");
    }

    public List<Iniciativa> listarIniciativas() {
        return XMLManager.readXML(WrapperIniciativa.class, Iniciativa.class, "Iniciativas.xml");
    }
}