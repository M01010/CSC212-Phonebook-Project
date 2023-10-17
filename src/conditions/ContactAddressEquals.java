package conditions;

import models.Contact;

/*************Example***************
 CLASS: ContactAddressEquals.java
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
public class ContactAddressEquals implements Condition<Contact> {
    private final String address;

    public ContactAddressEquals(String address) {
        this.address = address;
    }

    @Override
    public boolean test(Contact data) {
        return data.getAddress().equalsIgnoreCase(address);
    }


}
