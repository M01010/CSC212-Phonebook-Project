package conditions;

import models.Contact;

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
