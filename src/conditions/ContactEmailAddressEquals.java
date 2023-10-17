package conditions;

import models.Contact;
/*************Example***************
 CLASS: ContactEmailAddressEquals.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/12/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 Musad,    (ID443101092)
 ***********************************/
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
