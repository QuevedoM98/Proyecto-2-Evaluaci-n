package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaXML {

    private static final String ARCHIVO_XML = "usuarios.xml";

    public static void guardarUsuarios(List<Usuario> usuarios) {
        try {
            JAXBContext context = JAXBContext.newInstance(UserSeriarizable.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            UserSeriarizable wrapper = new UserSeriarizable();
            wrapper.setUsuarios(usuarios);

            marshaller.marshal(wrapper, new File(ARCHIVO_XML));
            System.out.println("Lista de usuarios guardada en " + ARCHIVO_XML);
        } catch (JAXBException e) {
            System.out.println("Error al guardar usuarios en XML: " + e.getMessage());
        }
    }

    public static List<Usuario> cargarUsuarios() {
        File archivo = new File(ARCHIVO_XML);
        if (!archivo.exists()) {
            System.out.println("Archivo no encontrado. Se devuelve lista vacía.");
            return new ArrayList<>();
        }

        try {
            JAXBContext context = JAXBContext.newInstance(UserSeriarizable.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UserSeriarizable wrapper = (UserSeriarizable) unmarshaller.unmarshal(archivo);
            return wrapper.getUsuarios();
        } catch (JAXBException e) {
            System.out.println("Error al cargar usuarios desde XML: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}


