package conditions;

import models.Contact;
import models.Event;

public class EventHasContactAndDateEquals implements Condition<Event> {
    private final String dateTime;
    private final Contact c;

    /**
     * Test: O(N)
     */
    public EventHasContactAndDateEquals(String dateTime, Contact c) {
        this.dateTime = dateTime;
        this.c = c;
    }
    @Override
    public boolean test(Event data) {
        return data.contactIsSchedueled(dateTime, c);
    }
}
