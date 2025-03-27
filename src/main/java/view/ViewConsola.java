package view;

import model.Creador;
import model.Iniciativa;

import java.util.List;

public class ViewConsola {

    public void listarIniciativas(Creador creador) {
        List<Iniciativa> iniciativas = creador.getIniciativas();
        if (iniciativas.isEmpty()) {
            System.out.println("No hay iniciativas creadas.");
        } else {
            System.out.println("Iniciativas creadas:");
            for (Iniciativa iniciativa : iniciativas) {
                System.out.println("Nombre: " + iniciativa.getNombreIniciativa());
                System.out.println("Descripción: " + iniciativa.getDescripcion());
                System.out.println("Creador: " + iniciativa.getCreador());
                System.out.println("-------------------------");
            }
        }
    }
}