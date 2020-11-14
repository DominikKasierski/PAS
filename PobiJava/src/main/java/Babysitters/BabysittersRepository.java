package Babysitters;

import Family.Family;
import Template.Repository;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BabysittersRepository extends Repository<Babysitter> {

    public String toString() {
        ToStringBuilder stringBuilder = new ToStringBuilder(this);
        stringBuilder.append(System.getProperty("line.separator"));

        for (Babysitter babysitter : getElements()) {
            stringBuilder.append("babysitter", babysitter.toString());
            stringBuilder.append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }
}
