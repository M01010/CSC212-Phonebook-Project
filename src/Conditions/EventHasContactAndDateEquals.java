package Conditions;

import models.Contact;
import models.Event;

public class EventHasContactAndDateEquals implements Condition<Event> {
    private final Event event;
    private final Contact c;
    public EventHasContactAndDateEquals(Event e, Contact c) {
        this.event = e;
        this.c = c;
    }
    @Override
    public boolean test(Event data) {
        return data.contactIsSchedueled(event, c);
    }
}
