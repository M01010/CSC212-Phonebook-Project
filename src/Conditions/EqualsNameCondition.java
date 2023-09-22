package Conditions;

import models.Contact;

public class EqualsNameCondition implements Condition<Contact> {

    private final String name;

    public EqualsNameCondition(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Contact data) {
        return data.getName().equalsIgnoreCase(name);
    }

}
