package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

    @XmlRootElement(name = "Personas")
    @XmlAccessorType(XmlAccessType.FIELD)
    class UserSeriarizable {
        private List<Usuario> usuarios;

        public List<Usuario> getUsuarios() {
            return usuarios;
        }

        public void setUsuarios(List<Usuario> usuarios) {
            this.usuarios = usuarios;
        }
    }
