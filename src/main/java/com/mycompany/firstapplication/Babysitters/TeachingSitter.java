package com.mycompany.firstapplication.Babysitters;

import com.mycompany.firstapplication.Exceptions.BabysitterException;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TeachingSitter extends Babysitter {

    private Integer yearsOfExperienceInTeaching;

    public TeachingSitter(String name, String surname, Integer basePriceForHour, Integer minChildAge,
                          Integer maxNumberOfChildrenInTheFamily, Integer yearsOfExperienceInTeaching) {
        super(name, surname, basePriceForHour, minChildAge, maxNumberOfChildrenInTheFamily);
        this.yearsOfExperienceInTeaching = yearsOfExperienceInTeaching;
        if (yearsOfExperienceInTeaching < 0) {
            throw new BabysitterException("Invalid argument");
        }
    }

    public TeachingSitter() {
    }

    @Override
    public double priceForHour() {
        return getBasePriceForHour() * (1 + yearsOfExperienceInTeaching / 10.0);
    }

    public void setYearsOfExperienceInTeaching(Integer yearsOfExperienceInTeaching) {
        this.yearsOfExperienceInTeaching = yearsOfExperienceInTeaching;
    }

    @Override
    public Integer getYearsOfExperienceInTeaching() {
        return yearsOfExperienceInTeaching;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("yearsOfExperienceInTeaching", yearsOfExperienceInTeaching)
                .toString();
    }
}
