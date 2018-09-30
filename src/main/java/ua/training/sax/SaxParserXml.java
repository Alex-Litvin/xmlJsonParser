package ua.training.sax;

import org.xml.sax.SAXException;
import ua.training.entity.Person;
import ua.training.util.ParserHelper;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static ua.training.util.ParserHelper.FILTERED_SAX_TXT;
import static ua.training.util.ParserHelper.PASS_CASH;

public class SaxParserXml {

    public static void main(String[] args) {
        ParserHelper parserHelper = new ParserHelper();
        File file = new File(ParserHelper.CATALOG_XML);
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxFactory.newSAXParser();
            PersonHandler personHandler = new PersonHandler();
            saxParser.parse(file, personHandler);
            List<Person> persons = personHandler.getPersons();
            List<Person> filteredPersons = parserHelper.filterPersons(persons, PASS_CASH);
            parserHelper.printToConsole(filteredPersons);
            parserHelper.writeToFile(filteredPersons, FILTERED_SAX_TXT);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
