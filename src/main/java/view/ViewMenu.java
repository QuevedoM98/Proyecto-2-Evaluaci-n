package view;

public class ViewMenu {
    public int ViewMenu() {
        int opcion = 0;
        System.out.println("\n ****** - Menu principal - ******");
        System.out.println("1- Crear iniciativa.");
        System.out.println("2- Ver actividades.");
        System.out.println("3- Cambiar de estado");
        System.out.println("4- Salir");
        System.out.println("****** - -------------- - ****** \n");
        do {
            opcion = utils.Utils.leeEntero("Elige una opción:");
            if (opcion < 1 || opcion > 4) {
                System.out.println("Opción no válida. Por favor, ingrese un número entre 1 y 4.");
            }
        } while (opcion < 1 || opcion > 4);

        switch (opcion) {
            case 1:
                System.out.println("Crear iniciativa");
                break;
            case 2:
                System.out.println("Ver actividades");
                break;
            case 3:
                System.out.println("Cambiar de estado");
                break;
            case 4:
                System.out.println("Saliendo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
        return opcion;
    }
}
