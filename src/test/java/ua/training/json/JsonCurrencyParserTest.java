package ua.training.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import ua.training.entity.Currency;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class JsonCurrencyParserTest {

    private String url;
    private ObjectMapper objectMapper;
    private static final Set<String> CURRENCIES = new HashSet<>();
    static {
        CURRENCIES.add("USD");
        CURRENCIES.add("EUR");
        CURRENCIES.add("RUB");
    }

    @Before
    public void setUp() throws Exception {
        url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getArrayOfCurrenciesWhenReadValueByUrl() throws IOException {
        Currency[] currencies = objectMapper.readValue(new URL(url), Currency[].class);
        assertTrue(currencies.length > 0);
    }

    @Test
    public void getThreeCurrenciesAfterFiltering() throws IOException {
        Currency[] currencies = objectMapper.readValue(new URL(url), Currency[].class);
        List<Currency> expectedList = Arrays.stream(currencies)
                .filter(c -> CURRENCIES.contains(c.getAcronym()))
                .collect(Collectors.toList());
        assertEquals(expectedList.size(), 3);
    }
}