package conditions;

import models.Contact;

public class ContactFirstNameEquals implements Condition<Contact> {
    final String fname;

    public ContactFirstNameEquals(String fname) {
        this.fname = fname;
    }

    @Override
    public boolean test(Contact data) {
        String userFirstName = data.getName().split(" ")[0];
        return fname.equalsIgnoreCase(userFirstName);
    }
}
