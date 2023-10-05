package Conditions;

import models.Contact;
import models.Event;

public class EventIsForContact implements Condition<Event> {
    private final Contact contact;
    public EventIsForContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean test(Event data) {
        Contact res = data.searchContacts(new ContactNameEquals(contact.getName()));
        return res != null;
    }
}
