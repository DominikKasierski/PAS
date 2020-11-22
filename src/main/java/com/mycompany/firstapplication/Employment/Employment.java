package com.mycompany.firstapplication.Employment;

import com.mycompany.firstapplication.Babysitters.Babysitter;
import com.mycompany.firstapplication.Exceptions.EmploymentException;
import com.mycompany.firstapplication.Family.Family;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.*;

public class Employment {

    private UUID uniqueID;
    private Babysitter babysitter;
    private Family family;
    private LocalDateTime beginningOfEmployment;
    private LocalDateTime endOfEmployment;

    public Employment(Babysitter babysitter, Family family) {
        this.babysitter = babysitter;
        this.family = family;
        uniqueID = UUID.randomUUID();
        beginningOfEmployment = LocalDateTime.now();
    }

    public void endEmployment() {
        endOfEmployment = LocalDateTime.now().plusMinutes(60).plusSeconds(1);
        //endOfEmployment = LocalDateTime.now();
        //tak powinno być, ale czasy początku i końca byłyby te same
    }

    public boolean isEnded() {
        return !(endOfEmployment == null);
    }

    public double employmentDurationInHours() {
        if (isEnded()) {
            long differenceInSeconds = SECONDS.between(beginningOfEmployment, endOfEmployment);
            return Math.ceil(differenceInSeconds / 3600.0);
        }
        throw new EmploymentException("Employment has not been ended");
    }

    public double employmentCost() {
        if (isEnded()) {
            return employmentDurationInHours() * babysitter.priceForHour();
        }
        throw new EmploymentException("Employment has not been ended");
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
                .append(System.getProperty("line.separator"))
                .append("uniqueID", uniqueID)
                .append(System.getProperty("line.separator"))
                .append("babysitter", babysitter)
                .append(System.getProperty("line.separator"))
                .append("family", family)
                .append(System.getProperty("line.separator"))
                .append("beginningOfEmployment", beginningOfEmployment)
                .append(System.getProperty("line.separator"))
                .append("endOfEmployment", endOfEmployment)
                .toString();
    }

    public LocalDateTime getEndOfEmployment() {
        return endOfEmployment;
    }

    public Babysitter getBabysitter() {
        return babysitter;
    }

    public Family getFamily() {
        return family;
    }

    public UUID getUuid() {
        return uniqueID;
    }
}
