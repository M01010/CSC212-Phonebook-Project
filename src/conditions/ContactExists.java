package conditions;

import models.Contact;
/*************Example***************
 CLASS: ContactExists.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/16/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 ***********************************/
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
