package conditions;

import models.Contact;
import models.Event;

public class EventContactNameEquals implements Condition<Event> {

    private final String name;

    public EventContactNameEquals(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Event data) {
        Contact res = data.searchContacts(new ContactNameEquals(name));
        return res != null;
    }

}
