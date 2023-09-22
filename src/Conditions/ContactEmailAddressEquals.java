package Conditions;

import models.Contact;

public class ContactEmailAddressEquals implements Condition<Contact> {

    private final String email;

    public ContactEmailAddressEquals(String email) {
        this.email = email;
    }

    @Override
    public boolean test(Contact data) {
        return data.getEmail().equalsIgnoreCase(email);
    }


}
