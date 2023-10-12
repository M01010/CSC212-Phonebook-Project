package conditions;

import models.Contact;

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
