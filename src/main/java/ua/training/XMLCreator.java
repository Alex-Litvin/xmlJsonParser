package ua.training;

import ua.training.entity.Catalog;
import ua.training.entity.Notebook;
import ua.training.entity.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class XMLCreator {
    public static void main(String[] args) {

        Catalog catalog = new Catalog();
        Notebook notebook = new Notebook();
        Person engineer = new Person(1, "John", "USA", new BigDecimal("12000"), "Engineer");
        Person teacher = new Person(2, "Tom", "Australia", new BigDecimal("11000"), "Teacher");
        Person doctor = new Person(3, "Max", "Canada", new BigDecimal("9000"), "Doctor");

        notebook.setPersons(Arrays.asList(engineer, teacher, doctor));
        catalog.setNotebook(notebook);

        try {
            File file = new File("src\\main\\resources\\catalog.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(catalog, file);
            jaxbMarshaller.marshal(catalog, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
