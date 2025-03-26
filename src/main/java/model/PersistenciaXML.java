package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaXML {

    public static <T> void guardarDatos(List<T> datos, Class<T> clase, String archivoXML) {
        try {
            JAXBContext context = JAXBContext.newInstance(clase);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(datos, new File(archivoXML));
            System.out.println("Datos guardados en " + archivoXML);
        } catch (JAXBException e) {
            System.out.println("Error al guardar datos en XML: " + e.getMessage());
        }
    }

    public static <T> List<T> cargarDatos(Class<T> clase, String archivoXML) {
        File archivo = new File(archivoXML);
        if (!archivo.exists()) {
            System.out.println("Archivo no encontrado. Se devuelve lista vacía.");
            return new ArrayList<>();
        }

        try {
            JAXBContext context = JAXBContext.newInstance(clase);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (List<T>) unmarshaller.unmarshal(archivo);
        } catch (JAXBException e) {
            System.out.println("Error al cargar datos desde XML: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}


