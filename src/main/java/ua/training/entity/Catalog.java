package ua.training.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Catalog {

    private Notebook notebook;

    public Catalog() {
    }

    @XmlElement(name = "notebook")
    public void setNotebook(Notebook notebook) {
        this.notebook = notebook;
    }

    public Notebook getNotebook() {
        return notebook;
    }
}
