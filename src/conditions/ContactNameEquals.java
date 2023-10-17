package conditions;

import models.Contact;
/*************Example***************
 CLASS: ContactNameEquals.java
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
public class ContactNameEquals implements Condition<Contact> {

    private final String name;

    public ContactNameEquals(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Contact data) {
        return data.getName().equalsIgnoreCase(name);
    }

}
