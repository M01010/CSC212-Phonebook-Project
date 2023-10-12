package conditions;

import models.Contact;

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
