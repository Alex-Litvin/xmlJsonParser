package ua.training.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ua.training.entity.Currency;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class JsonCurrencyParser {

    private static final String BANK_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final String FILE_PATH = "src\\main\\resources\\currency.json";

    public static void main(String[] args) {
        JsonCurrencyParser jsonCurrencyParser = new JsonCurrencyParser();
        try {
            jsonCurrencyParser.parse();
            jsonCurrencyParser.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final Set<String> CURRENCIES = new HashSet<>();

    static {
        CURRENCIES.add("USD");
        CURRENCIES.add("EUR");
        CURRENCIES.add("RUB");
    }

    private void parse() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        Currency[] currency = readValue(mapper, BANK_URL);
        List<Currency> actualCurrencies = getActualCurrencies(currency);
        writeToFile(mapper, actualCurrencies);
    }

    private Currency[] readValue(ObjectMapper mapper, String url) throws IOException {
        return mapper.readValue(new URL(url), Currency[].class);
    }

    private List<Currency> getActualCurrencies(Currency[] currency) {
        return Arrays.stream(currency)
                .filter(c -> CURRENCIES.contains(c.getAcronym()))
                .collect(Collectors.toList());
    }

    private void writeToFile(ObjectMapper mapper, List<Currency> currencies) {
        String jsonValue;
        try {
            jsonValue = mapper.writeValueAsString(currencies);
            Files.write(Paths.get(FILE_PATH), jsonValue.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        File file;
        try {
            file = new File(FILE_PATH);
            Currency[] currencies = mapper.readValue(file, Currency[].class);
            printToConsole(Arrays.asList(currencies));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printToConsole(List<Currency> currencies) {
        currencies.forEach(System.out::println);
    }
}
