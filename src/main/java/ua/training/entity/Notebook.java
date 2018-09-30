package ua.training.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Notebook {

    private List<Person> persons;

    public Notebook() {
    }

    @XmlElement(name = "person")
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }
}
