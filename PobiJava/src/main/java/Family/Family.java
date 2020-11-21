package Family;

import Exceptions.FamilyException;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Family {

    private List<Child> children = new ArrayList<>();

    private UUID uniqueID;

    public Family() {
        uniqueID = UUID.randomUUID();
    }

    public void addChild(Child child) {
        for (Child kid : children) {
            if (kid == child) {
                throw new FamilyException("Child already exists");
            }
        }
        children.add(child);
    }

    public void deleteChild(Child child) {
        for (Child kid : children) {
            if (kid == child) {
                children.remove(child);
                return;
            }
        }
        throw new FamilyException("Child does not exists");
    }

    public int getNumberOfChildren() {
        return children.size();
    }

    public int getAgeOfTheYoungestChild() {
        return children
                .stream()
                .mapToInt(Child::getAge)
                .min()
                .orElseThrow(() -> new FamilyException("This family has no children"));
    }

    public UUID getUuid() {
        return uniqueID;
    }

    @Override public String toString() {
        ToStringBuilder stringBuilder = new ToStringBuilder(this);
        stringBuilder.append(System.getProperty("line.separator"));

        for (Child child : children) {
            stringBuilder.append("child", child.toString());
            stringBuilder.append(System.getProperty("line.separator"));
        }

        return stringBuilder.toString();
    }

    public List<Child> getChildren() {
        return new ArrayList<>(children);
    }
}
