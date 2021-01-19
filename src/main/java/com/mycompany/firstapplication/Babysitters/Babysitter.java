package com.mycompany.firstapplication.Babysitters;

import com.mycompany.firstapplication.Exceptions.BabysitterException;

public class Babysitter implements Cloneable {

    private String name;
    private String surname;
    private Integer basePriceForHour;
    private Integer minChildAge;
    private Integer maxNumberOfChildrenInTheFamily;
    private boolean employed;
    private String uniqueID;

    public Babysitter() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Babysitter(String name, String surname, Integer basePriceForHour, Integer minChildAge,
                      int maxNumberOfChildrenInTheFamily) {
        this.name = name;
        this.surname = surname;
        this.basePriceForHour = basePriceForHour;
        this.minChildAge = minChildAge;
        this.maxNumberOfChildrenInTheFamily = maxNumberOfChildrenInTheFamily;
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

    public boolean isEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        this.employed = employed;
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

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    @Override
    public String toString() {
        return "name: " + name +
                "\nsurname: " + surname +
                "\nbasePrice: " + basePriceForHour +
                "\nminChildAge: " + minChildAge +
                "\nmaxNumberOfChildrenInTheFamily: " + minChildAge;
    }
}
