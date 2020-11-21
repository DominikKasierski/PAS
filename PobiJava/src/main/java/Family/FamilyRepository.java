package Family;

import Employment.Employment;
import Exceptions.RepositoryException;
import Template.Repository;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.UUID;


public class FamilyRepository extends Repository<Family> {

    public void addChildToFamily(Family family, Child child) {
        family.addChild(child);
    }

    public void deleteChildFromFamily(Family family, Child child) {
        family.deleteChild(child);
    }

    public int getNumberOfChildrenInTheFamily(Family family) {
        return family.getNumberOfChildren();
    }

    public Family findByKey(UUID uuid) {
        List<Family> familyList = getElements();

        for (Family family : familyList) {
            if (family.getUuid() == uuid) {
                return family;
            }
        }
        throw new RepositoryException("Element not found");
    }

    public String toString() {
        ToStringBuilder stringBuilder = new ToStringBuilder(this);
        stringBuilder.append(System.getProperty("line.separator"));

        for (Family family : getElements()) {
            stringBuilder.append("family", family.toString());
            stringBuilder.append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }
}
