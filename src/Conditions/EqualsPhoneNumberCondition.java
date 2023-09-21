package Conditions;

import models.Contact;

public class EqualsPhoneNumberCondition implements Condition<Contact> {

    private final String PhoneNumber;

    public EqualsPhoneNumberCondition(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    @Override
    public boolean test(Contact data) {
        return data.phoneNumber.equalsIgnoreCase(PhoneNumber);
    }

}
