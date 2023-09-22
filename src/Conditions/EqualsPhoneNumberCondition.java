package Conditions;

import models.Contact;

public class EqualsPhoneNumberCondition implements Condition<Contact> {

    private final String phoneNumber;

    public EqualsPhoneNumberCondition(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean test(Contact data) {
        return data.getPhoneNumber().equalsIgnoreCase(phoneNumber);
    }

}
