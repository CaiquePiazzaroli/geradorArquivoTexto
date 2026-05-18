package model;

import enums.Gender;
import interfaces.PositionableLayoutObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class Client implements PositionableLayoutObject {

    private String name;
    private LocalDate dateBirth;
    private Gender gender;
    private BigDecimal wage;


    // Necessary to use Reflection Api
    public Client() {}


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

    @Override
    public Map<String,Integer> getCharacterNumberFields() {
        Map<String, Integer> characterNumberFields = new LinkedHashMap<>();

        characterNumberFields.put("getName", 30);
        characterNumberFields.put("getDateBirth", 40);
        characterNumberFields.put("getGender", 46);
        characterNumberFields.put("getWage", 56);

        return characterNumberFields;
    }
}
