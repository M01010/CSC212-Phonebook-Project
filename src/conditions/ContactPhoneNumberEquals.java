package conditions;

import models.Contact;
/*************Example***************
 CLASS: ContactPhoneNumberEquals.java
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
public class ContactPhoneNumberEquals implements Condition<Contact> {

    private final String phoneNumber;

    public ContactPhoneNumberEquals(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean test(Contact data) {
        return data.getPhoneNumber().equalsIgnoreCase(phoneNumber);
    }

}
