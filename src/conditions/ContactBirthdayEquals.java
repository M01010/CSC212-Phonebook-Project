package conditions;

import models.Contact;
/*************Example***************
 CLASS: ContactBirthdayEquals.java
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
public class ContactBirthdayEquals implements Condition<Contact> {
    private final String birthdate;

    public ContactBirthdayEquals(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean test(Contact data) {
        return data.getBirthDate().equalsIgnoreCase(birthdate);
    }

}
