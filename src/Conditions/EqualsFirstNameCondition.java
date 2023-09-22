package Conditions;

import models.Contact;

public class EqualsFirstNameCondition implements Condition<Contact> {
    final String fname;
    public EqualsFirstNameCondition(String fname) {
        this.fname = fname;
    }

    @Override
    public boolean test(Contact data) {
        String userFirstName = data.getName().split(" ")[0];
        return fname.equalsIgnoreCase(userFirstName);
    }
}
