package Babysitters;

import Exceptions.RepositoryException;
import Family.Family;
import Template.Repository;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.UUID;

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
