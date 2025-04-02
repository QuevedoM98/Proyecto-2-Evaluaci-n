package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "creadores")
public class WrapperCreador extends Wrapper<Creador> {

    @XmlElement(name = "creador")
    public List<Creador> getItems() {
        return super.getItems();
    }

    public void setItems(List<Creador> items) {
        super.setItems(items);
    }
}
