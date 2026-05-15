package model;

import enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Client {

    private String name;
    private LocalDate dateBirth;
    private Gender gender;
    private BigDecimal wage;

    public Client(String name, LocalDate dateBirth, Gender gender, BigDecimal wage) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.gender = gender;
        this.wage = wage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }
}
