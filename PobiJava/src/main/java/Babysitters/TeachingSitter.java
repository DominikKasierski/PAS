package Babysitters;

import Exceptions.BabysitterException;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TeachingSitter extends Babysitter {

    private int yearsOfExperienceInTeaching;

    public TeachingSitter(String name, String surname, int basePrice, int minChildAge,
                          int maxNumberOfChildrenInTheFamily, int yearsOfExperienceInTeaching) {
        super(name, surname, basePrice, minChildAge, maxNumberOfChildrenInTheFamily);
        this.yearsOfExperienceInTeaching = yearsOfExperienceInTeaching;
        if (yearsOfExperienceInTeaching < 0) {
            throw new BabysitterException("Invalid argument");
        }
    }

    @Override public double price() {
        return getBasePrice() * (1 + yearsOfExperienceInTeaching / 10.0);
    }

    public int getYearsOfExperienceInTeaching() {
        return yearsOfExperienceInTeaching;
    }

    @Override public String toString() { //TODO: SPRAWDZIC CZY WYPISZE IMIE NAZWISKO ITD
        return new ToStringBuilder(this)
                .append("yearsOfExperienceInTeaching", yearsOfExperienceInTeaching)
                .toString();
    }
}
