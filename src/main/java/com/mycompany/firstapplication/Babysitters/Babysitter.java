package com.mycompany.firstapplication.Babysitters;

import com.mycompany.firstapplication.Exceptions.BabysitterException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.UUID;

public class Babysitter {

    private String name;
    private String surname;
    private Integer basePriceForHour;
    private Integer minChildAge;
    private Integer maxNumberOfChildrenInTheFamily;
    private final String uniqueID;
    final int SHORT_ID_LENGTH = 8;

    public Babysitter() {
        uniqueID = RandomStringUtils.randomNumeric(SHORT_ID_LENGTH);
    }

    public Babysitter(String name, String surname, Integer basePriceForHour, Integer minChildAge,
            int maxNumberOfChildrenInTheFamily) {
        this.name = name;
        this.surname = surname;
        this.basePriceForHour = basePriceForHour;
        this.minChildAge = minChildAge;
        this.maxNumberOfChildrenInTheFamily = maxNumberOfChildrenInTheFamily;
        uniqueID = RandomStringUtils.randomNumeric(SHORT_ID_LENGTH);
        if (basePriceForHour <= 0 || minChildAge < 0 || maxNumberOfChildrenInTheFamily <= 0) {
            throw new BabysitterException("Invalid argument");
        }
    }

    public double priceForHour() {
        return basePriceForHour;
    }

    public Integer getYearsOfExperienceInTeaching() {
        return null;
    }
    public Double getValueOfCleaningEquipment() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBasePriceForHour(Integer basePriceForHour) {
        this.basePriceForHour = basePriceForHour;
    }

    public void setMinChildAge(Integer minChildAge) {
        this.minChildAge = minChildAge;
    }

    public void setMaxNumberOfChildrenInTheFamily(Integer maxNumberOfChildrenInTheFamily) {
        this.maxNumberOfChildrenInTheFamily = maxNumberOfChildrenInTheFamily;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getBasePriceForHour() {
        return basePriceForHour;
    }

    public Integer getMinChildAge() {
        return minChildAge;
    }

    public Integer getMaxNumberOfChildrenInTheFamily() {
        return maxNumberOfChildrenInTheFamily;
    }

    public String getUuid() {
        return uniqueID;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("surname", surname)
                .append("basePrice", basePriceForHour)
                .append("minChildAge", minChildAge)
                .append("maxNumberOfChildrenInTheFamily", maxNumberOfChildrenInTheFamily)
                .toString();
    }
}
