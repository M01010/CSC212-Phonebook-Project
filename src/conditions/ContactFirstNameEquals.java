package conditions;

import models.Contact;
/*************Example***************
 CLASS: ContactFirstNameEquals.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/17/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 ***********************************/
public class ContactFirstNameEquals implements Condition<Contact> {
    private final String fname;

    public ContactFirstNameEquals(String fname) {
        this.fname = fname;
    }

    @Override
    public boolean test(Contact data) {
        String userFirstName = data.getName().split(" ")[0];
        return fname.equalsIgnoreCase(userFirstName);
    }
}
