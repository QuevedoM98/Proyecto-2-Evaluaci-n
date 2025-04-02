package DataAccess;

import model.Wrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLManager {

    public static <T> List<T> readXML(Class<? extends Wrapper<T>> wrapperClass, Class<T> clazz, String archivoXML) {
        File archivo = new File(archivoXML);
        if (!archivo.exists() || archivo.length() == 0) {
            System.out.println("Archivo no encontrado o vacío. Inicializando archivo.");
            return new ArrayList<>();
        }

        try {
            JAXBContext context = JAXBContext.newInstance(wrapperClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Wrapper<T> wrapper = wrapperClass.cast(unmarshaller.unmarshal(archivo));
            List<T> items = wrapper.getItems();
            System.out.println("Leído del archivo " + archivoXML + ": " + (items != null ? items.size() : 0) + " elementos.");
            return items != null ? items : new ArrayList<>();
        } catch (JAXBException e) {
            e.printStackTrace(); // Añadido para depuración
            throw new RuntimeException("Error al leer el archivo XML", e);
        }
    }

    public static <T> void writeXML(Class<? extends Wrapper<T>> wrapperClass, List<T> lista, String archivoXML) {
        try {
            JAXBContext context = JAXBContext.newInstance(wrapperClass);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Wrapper<T> wrapper = wrapperClass.getDeclaredConstructor().newInstance();
            wrapper.setItems(lista);

            marshaller.marshal(wrapper, new File(archivoXML));
            System.out.println("Escrito en el archivo " + archivoXML + ": " + lista.size() + " elementos.");
        } catch (JAXBException | ReflectiveOperationException e) {
            throw new RuntimeException("Error al escribir el archivo XML", e);
        }
    }
}
