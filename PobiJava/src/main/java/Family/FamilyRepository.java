package Family;

import Template.Repository;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class FamilyRepository extends Repository<Family> {

    public boolean checkIfFamilyIsInRepository(Family family) {
        return super.checkIfTheElementIsPresent(family);
    }

    public void addFamilyToRepository(Family family) {
        super.addElement(family);
    }

    public void deleteFamilyFromRepository(Family family) {
        super.deleteElement(family);
    }

    public int getNumberOfFamiliesInRepository() {
        return super.getNumberOfElements();
    }

    public List<Family> getFamiliesList() {
        return super.getElements();
    }

    public void addChildToFamily(Family family, Child child) {
        family.addChild(child);
    }

    public void deleteChildFromFamily(Family family, Child child) {
        family.deleteChild(child);
    }

    public int getNumberOfChildrenInTheFamily(Family family) {
        return family.getNumberOfChildren();
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
