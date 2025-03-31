package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaXML {

    public static <T> void guardarDatos(List<T> datos, Class<T> clase, String archivoXML) {
        try {
            JAXBContext context = JAXBContext.newInstance(clase);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Wrapper<>(datos), new File(archivoXML));
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
            Wrapper<T> wrapper = (Wrapper<T>) unmarshaller.unmarshal(archivo);
            return wrapper.getItems();
        } catch (JAXBException e) {
            System.out.println("Error al cargar datos desde XML: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @XmlRootElement(name = "wrapper")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Wrapper<T> {
        private List<T> items;

        public Wrapper() {
            items = new ArrayList<>();
        }

        public Wrapper(List<T> items) {
            this.items = items;
        }

        public List<T> getItems() {
            return items;
        }

        public void setItems(List<T> items) {
            this.items = items;
        }
    }
}

