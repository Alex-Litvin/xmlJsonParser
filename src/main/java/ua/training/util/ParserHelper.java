package ua.training.util;

import ua.training.entity.Person;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ParserHelper {

    public static final BigDecimal PASS_CASH = new BigDecimal("10000");
    public static final String CATALOG_XML = "src\\main\\resources\\catalog.xml";
    public static final String FILTERED_SAX_TXT = "src\\main\\resources\\filteredPersonsSax";
    public static final String FILTERED_DOM_TXT = "src\\main\\resources\\filteredPersonsDom";
    public static final String PERSON_TAG = "person";
    public static final String PERSON_ID_ATTRIBUTE = "id";
    public static final String NAME_TAG = "name";
    public static final String ADDRESS_TAG = "address";
    public static final String CASH_TAG = "cash";

    public List<Person> filterPersons(List<Person> persons, BigDecimal pass_cash) {
        return persons.stream()
                .filter(person -> person.getCash().compareTo(pass_cash) >= 0)
                .collect(Collectors.toList());
    }

    public void printToConsole(List<Person> persons) {
        persons.forEach(System.out::println);
    }

    public void writeToFile(List<Person> persons, String filepath) {
        String personsForWrite = persons.stream()
                .map(Person::toString)
                .collect(Collectors.joining(", \n"));

        Path path = Paths.get(filepath);
        try {
            Files.write(path, personsForWrite.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
