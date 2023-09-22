package Conditions;

import models.Contact;

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
