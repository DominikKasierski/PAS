package Employment;

import Template.Repository;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class EmploymentRepository extends Repository<Employment> {

    public String toString() {
        ToStringBuilder stringBuilder = new ToStringBuilder(this);
        stringBuilder.append(System.getProperty("line.separator"));

        for (Employment employment : getElements()) {
            stringBuilder.append("babysitter", employment.toString());
            stringBuilder.append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }
}
