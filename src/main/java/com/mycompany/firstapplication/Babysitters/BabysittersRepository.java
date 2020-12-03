package com.mycompany.firstapplication.Babysitters;


import com.mycompany.firstapplication.Exceptions.RepositoryException;
import com.mycompany.firstapplication.Template.Repository;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BabysittersRepository extends Repository<Babysitter> {

    public Babysitter findByKey(UUID uuid) {
        List<Babysitter> babysitterList = getElements();

        for (Babysitter babysitter : babysitterList) {
            if (babysitter.getUuid() == uuid) {
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
}
