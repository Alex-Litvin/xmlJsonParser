package ua.training.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Currency {

    @JsonProperty("r030")
    private Integer id;

    @JsonProperty("txt")
    private String name;

    @JsonProperty("rate")
    private BigDecimal rate;

    @JsonProperty("cc")
    private String acronym;

    @JsonProperty("exchangedate")
    private String date;

    public Currency() {
    }

    public Currency(Integer id, String name, BigDecimal rate, String acronym, String date) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.acronym = acronym;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                ", acronym='" + acronym + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
