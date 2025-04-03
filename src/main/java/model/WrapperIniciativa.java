package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "iniciativas")
public class WrapperIniciativa extends Wrapper<Iniciativa> {

    public WrapperIniciativa() {
        super();
    }

    @XmlElement(name = "iniciativa")
    public List<Iniciativa> getItems() {
        return super.getItems();
    }

    public void setItems(List<Iniciativa> items) {
        super.setItems(items);
    }
}
