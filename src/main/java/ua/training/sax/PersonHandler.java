package ua.training.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ua.training.entity.Person;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PersonHandler extends DefaultHandler {

    private static final String PERSON_TAG = "person";
    private static final String PERSON_ID_ATTRIBUTE = "id";
    private static final String NAME_TAG = "name";
    private static final String ADDRESS_TAG = "address";
    private static final String CASH_TAG = "cash";

    private List<Person> persons = new ArrayList<>();
    private Person person;
    private boolean name;
    private boolean address;
    private boolean cash;

    List<Person> getPersons() {
        return persons;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case PERSON_TAG:
                person = new Person();
                String id = attributes.getValue(PERSON_ID_ATTRIBUTE);
                person.setId(Integer.parseInt(id));
                break;
            case NAME_TAG:
                name = true;
                break;
            case ADDRESS_TAG:
                address = true;
                break;
            case CASH_TAG:
                cash = true;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(PERSON_TAG)) {
            persons.add(person);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (name) {
            person.setName(new String(ch, start, length));
            name = false;
        } else if (address) {
            person.setAddress(new String(ch, start, length));
            address = false;
        } else if (cash) {
            person.setCash(new BigDecimal(ch, start, length));
            cash = false;
        }
    }
}
