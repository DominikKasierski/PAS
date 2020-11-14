package Babysitters;

import Exceptions.BabysitterException;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Babysitter {

    private String name;
    private String surname;
    private int basePriceForHour;
    private int minChildAge;
    private int maxNumberOfChildrenInTheFamily;

    public Babysitter(String name, String surname, int basePriceForHour, int minChildAge,
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getBasePriceForHour() {
        return basePriceForHour;
    }

    public int getMinChildAge() {
        return minChildAge;
    }

    public int getMaxNumberOfChildrenInTheFamily() {
        return maxNumberOfChildrenInTheFamily;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("surname", surname)
                .append("basePrice", basePriceForHour)
                .append("minChildAge", minChildAge)
                .append("maxNumberOfChildrenInTheFamily", maxNumberOfChildrenInTheFamily)
                .toString();
    }
}
