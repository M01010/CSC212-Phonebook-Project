package Conditions;

import models.Event;

public class EventContactNameOrDateEquals implements Condition<Event> {
    private final Event event;
    public EventContactNameOrDateEquals(Event e) {
        this.event = e;
    }
    @Override
    public boolean test(Event data) {
        return this.event.contactIsSchedueled(data);
    }
}
