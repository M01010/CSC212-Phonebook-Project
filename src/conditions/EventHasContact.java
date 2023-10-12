package conditions;

import models.Contact;
import models.Event;

public class EventHasContact implements Condition<Event> {
    private final Contact contact;


    /**
     * Test: O(N)
     */
    public EventHasContact(Contact contact) {
        this.contact = contact;
    }
    @Override
    public boolean test(Event data) {
        Contact res = data.searchContacts(new ContactNameEquals(contact.getName()));
        return res != null;
    }
}
