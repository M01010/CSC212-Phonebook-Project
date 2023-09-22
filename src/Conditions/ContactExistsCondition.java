package Conditions;

import models.Contact;

public class ContactExistsCondition implements Condition<Contact> {
    private final Contact contact;

    public ContactExistsCondition(Contact c) {
        this.contact = c;
    }
    @Override
    public boolean test(Contact data) {
        return contact.getName().equalsIgnoreCase(data.getName()) || contact.getPhoneNumber().equalsIgnoreCase(data.getPhoneNumber());
    }
}
