package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "colaboradores")
public class WrapperColaborador extends Wrapper<Colaborador> {
    @XmlElement(name = "colaborador")
    private List<Colaborador> items;

    @Override
    public List<Colaborador> getItems() {
        return items;
    }

    @Override
    public void setItems(List<Colaborador> items) {
        this.items = items;
    }
}
