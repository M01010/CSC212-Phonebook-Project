package Conditions;

import models.Contact;

public class EqulasNameCondition implements Condition<Contact> {

    private final String name;

    public EqulasNameCondition(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Contact data) {
        return data.name.equalsIgnoreCase(name);
    }

}
