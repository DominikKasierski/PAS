package com.mycompany.firstapplication.Babysitters;


import com.mycompany.firstapplication.Exceptions.RepositoryException;
import com.mycompany.firstapplication.Template.Repository;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class BabysittersRepository extends Repository<Babysitter> {

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

    @PostConstruct
    private void initBabysittersList(){
        this.addElement(new Babysitter("Anna", "Kwiatkowska", 123,12,4));
        this.addElement(new Babysitter("Kinga", "Rusin", 50,5,4));
        this.addElement(new Babysitter("Joanna", "Krupa", 40,7,2));
    }
}
