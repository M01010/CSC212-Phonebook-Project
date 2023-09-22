package Conditions;

import models.Contact;

public class EqualsBirthdayCondition implements Condition<Contact> {
    private final String birthdate;

    public EqualsBirthdayCondition(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean test(Contact data) {
        return data.getBirthDate().equalsIgnoreCase(birthdate);
    }

}
