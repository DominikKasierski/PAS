package Family;

public class FamilyManager {

    private FamilyRepository familyRepository;

    public FamilyManager(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public void addFamily(Family family) {
        familyRepository.addElement(family);
    }

    public void deleteFamily(Family family) {
        familyRepository.deleteElement(family);
    }

    public void addChildToFamily(Family family, Child child) {
        familyRepository.addChildToFamily(family, child);
    }

    public void deleteChildFromFamily(Family family, Child child) {
        familyRepository.deleteChildFromFamily(family, child);
    }

    public int getNumberOfFamiliesInRepository() {
        return familyRepository.getNumberOfElements();
    }

    public int getNumberOfChildrenInTheFamily(Family family) {
        return familyRepository.getNumberOfChildrenInTheFamily(family);
    }

}
