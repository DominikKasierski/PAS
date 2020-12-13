package com.mycompany.firstapplication.Babysitters;

import com.mycompany.firstapplication.Exceptions.BabysitterException;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TidingSitter extends Babysitter {

    private Double valueOfCleaningEquipment;

    public TidingSitter(String name, String surname, Integer basePriceForHour, Integer minChildAge,
                        Integer maxNumberOfChildrenInTheFamily, double valueOfCleaningEquipment) {
        super(name, surname, basePriceForHour, minChildAge, maxNumberOfChildrenInTheFamily);
        this.valueOfCleaningEquipment = valueOfCleaningEquipment;
        if (valueOfCleaningEquipment < 0) {
            throw new BabysitterException("Invalid argument");
        }
    }

    public TidingSitter() {
    }

    @Override
    public double priceForHour() {
        return getBasePriceForHour() * (1 + valueOfCleaningEquipment / 500.0);
    }

    public void setValueOfCleaningEquipment(Double valueOfCleaningEquipment) {
        this.valueOfCleaningEquipment = valueOfCleaningEquipment;
    }

    @Override
    public Double getValueOfCleaningEquipment() {
        return valueOfCleaningEquipment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("valueOfCleaningEquipment", valueOfCleaningEquipment)
                .toString();
    }
}
