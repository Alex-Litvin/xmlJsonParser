package ua.training.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
public class Person {
    private int id;
    private String name;
    private String address;
    private BigDecimal cash;
    private String education;

    public Person() {
    }

    public Person(int id, String name, String address, BigDecimal cash, String education) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cash = cash;
        this.education = education;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getCash() {
        return cash;
    }

    @XmlElement
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public String getEducation() {
        return education;
    }

    @XmlElement
    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cash=" + cash + "}";
    }
}
