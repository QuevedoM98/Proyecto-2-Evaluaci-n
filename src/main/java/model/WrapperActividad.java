package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "actividades")
public class WrapperActividad extends Wrapper<Actividad> {

    @XmlElement(name = "actividad")
    public List<Actividad> getItems() {
        return super.getItems();
    }

    public void setItems(List<Actividad> items) {
        super.setItems(items);
    }
}
