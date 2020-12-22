package com.mycompany.firstapplication.Babysitters;


import com.mycompany.firstapplication.Exceptions.RepositoryException;
import com.mycompany.firstapplication.Template.Repository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BabysittersRepository extends Repository<Babysitter> {

    final int SHORT_ID_LENGTH = 8;

    @Override public void addElement(Babysitter element) {
        element.setUniqueID(RandomStringUtils.randomNumeric(SHORT_ID_LENGTH));
        super.addElement(element);
    }

    public List<Babysitter> getBabysittersList() {
        return getElements();
    }

    public Babysitter findByKey(String uuid) {
        List<Babysitter> babysitterList = getElements();

        for (Babysitter babysitter : babysitterList) {
            if (babysitter.getUuid().equals(uuid)) {
                return babysitter;
            }
        }
        throw new RepositoryException("Element not found");
    }

    public String toString() {
        ToStringBuilder stringBuilder = new ToStringBuilder(this);
        stringBuilder.append(System.getProperty("line.separator"));

        for (Babysitter babysitter : getElements()) {
            stringBuilder.append("babysitter", babysitter.toString());
            stringBuilder.append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }

    public List<Babysitter> showSelectedBabysitter(String id) {
        List<Babysitter> temporaryBabysittersList = new ArrayList<>();
        temporaryBabysittersList.add(findByKey(id));
        return temporaryBabysittersList;
    }

    @Override
    public void deleteElement(Babysitter babysitter) {
        super.deleteElement(babysitter);
    }

    @PostConstruct
    private void initBabysittersList() {
        addElement(new Babysitter("Anna", "Kwiatkowska", 123, 12, 4));
        addElement(new Babysitter("Kinga", "Rusin", 50, 4, 4));
        addElement(new Babysitter("Joanna", "Krupa", 40, 7, 2));
        addElement(new TidingSitter("Renia", "Sprzatajaca", 60, 1, 2, 40));
        addElement(new TeachingSitter("Jola", "Uczaca", 100, 4, 1, 3));
    }
}
