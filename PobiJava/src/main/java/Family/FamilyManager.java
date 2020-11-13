package Family;

public class FamilyManager {

    private FamilyRepository familyRepository;

    public FamilyManager(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
    }

    public void addFamily(Family family) {
        familyRepository.addFamilyToRepository(family);
    }

    public void deleteFamily(Family family) {
        familyRepository.deleteFamilyFromRepository(family);
    }

    public void addChildToFamily(Family family, Child child) {
        familyRepository.addChildToFamily(family, child);
    }

    public void deleteChildFromFamily(Family family, Child child) {
        familyRepository.deleteChildFromFamily(family, child);
    }

    public int getNumberOfFamiliesInRepository() {
        return familyRepository.getNumberOfFamiliesInRepository();
    }

    public int getNumberOfChildrenInTheFamily(Family family) {
        return familyRepository.getNumberOfFamiliesInRepository();
    }

}
