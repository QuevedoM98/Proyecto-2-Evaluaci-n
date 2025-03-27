package dataAccess;

import model.PersistenciaXML;
import java.util.List;

    public class XmlManager {

        public static <T> void guardarDatos(List<T> datos, Class<T> clase, String archivoXML) {
            PersistenciaXML.guardarDatos(datos, clase, archivoXML);
        }

        public static <T> List<T> cargarDatos(Class<T> clase, String archivoXML) {
            return PersistenciaXML.cargarDatos(clase, archivoXML);
        }
    }

