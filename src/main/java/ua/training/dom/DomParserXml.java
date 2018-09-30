package ua.training.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.training.entity.Person;
import ua.training.util.ParserHelper;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static ua.training.util.ParserHelper.*;

public class DomParserXml {

    public static void main(String[] args) {
        DomParserXml domParserXml = new DomParserXml();
        ParserHelper parserHelper = new ParserHelper();
        List<Person> persons = domParserXml.parseToList(CATALOG_XML);
        List<Person> filteredPersons = parserHelper.filterPersons(persons, PASS_CASH);
        parserHelper.printToConsole(filteredPersons);
        parserHelper.writeToFile(filteredPersons, FILTERED_DOM_TXT);
    }

    private List<Person> parseToList(String xmlPath) {
        File xmlFile = new File(xmlPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        List<Person> persons = new ArrayList<>();
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(PERSON_TAG);
            for (int i = 0; i < nodeList.getLength(); i++) {
                persons.add(parsePerson(nodeList.item(i)));
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return persons;
    }

    private Person parsePerson(Node node) {
        Person person = new Person();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            person.setId(Integer.parseInt(element.getAttribute(PERSON_ID_ATTRIBUTE)));
            person.setName(getTagValue(NAME_TAG, element));
            person.setAddress(getTagValue(ADDRESS_TAG, element));
            person.setCash(new BigDecimal(getTagValue(CASH_TAG, element)));
        }
        return person;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
