package conditions;

import models.Contact;

public class ContactExists implements Condition<Contact> {
    private final Contact contact;

    public ContactExists(Contact c) {
        this.contact = c;
    }

    @Override
    public boolean test(Contact data) {
        return contact.getName().equalsIgnoreCase(data.getName()) || contact.getPhoneNumber().equalsIgnoreCase(data.getPhoneNumber());
    }
}
