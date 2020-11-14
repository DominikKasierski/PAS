package Babysitters;

import Exceptions.BabysitterException;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TeachingSitter extends Babysitter {

    private int yearsOfExperienceInTeaching;

    public TeachingSitter(String name, String surname, int basePriceForHour, int minChildAge,
                          int maxNumberOfChildrenInTheFamily, int yearsOfExperienceInTeaching) {
        super(name, surname, basePriceForHour, minChildAge, maxNumberOfChildrenInTheFamily);
        this.yearsOfExperienceInTeaching = yearsOfExperienceInTeaching;
        if (yearsOfExperienceInTeaching < 0) {
            throw new BabysitterException("Invalid argument");
        }
    }

    @Override public double priceForHour() {
        return getBasePriceForHour() * (1 + yearsOfExperienceInTeaching / 10.0);
    }

    public int getYearsOfExperienceInTeaching() {
        return yearsOfExperienceInTeaching;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("yearsOfExperienceInTeaching", yearsOfExperienceInTeaching)
                .toString();
    }
}
