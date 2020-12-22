package com.mycompany.firstapplication.Employment;


import com.mycompany.firstapplication.Exceptions.RepositoryException;
import com.mycompany.firstapplication.Template.Repository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EmploymentsRepository extends Repository<Employment> {

    final int SHORT_ID_LENGTH = 8;

    @Override public void addElement(Employment element) {
        element.setUniqueID(RandomStringUtils.randomNumeric(SHORT_ID_LENGTH));
        super.addElement(element);
    }

    public Employment findByKey(String uuid) {
        List<Employment> employmentList = getElements();

        for (Employment employment : employmentList) {
            if (employment.getUuid().equals(uuid)) {
                return employment;
            }
        }
        throw new RepositoryException("Element not found");
    }

    public String toString() {
        ToStringBuilder stringBuilder = new ToStringBuilder(this);
        stringBuilder.append(System.getProperty("line.separator"));

        for (Employment employment : getElements()) {
            stringBuilder.append("employment", employment.toString());
            stringBuilder.append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }

}
