package Employment;

import Babysitters.Babysitter;
import Exceptions.RepositoryException;
import Template.Repository;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.UUID;

public class EmploymentRepository extends Repository<Employment> {

    public Employment findByKey(UUID uuid) {
        List<Employment> employmentList = getElements();

        for (Employment employment : employmentList) {
            if (employment.getUuid() == uuid) {
                return employment;
            }
        }
        throw new RepositoryException("Element not found");
    }

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
