package Conditions;

import models.Contact;

public class EqualsEmailAddressCondition implements Condition<Contact> {

    private final String email;

    public EqualsEmailAddressCondition(String email) {
        this.email = email;
    }

    @Override
    public boolean test(Contact data) {
        return data.email.equalsIgnoreCase(email);
    }


}
