package Conditions;

import models.Event;

public class EqualsNameDateCondition implements Condition<Event> {
    private final Event event;
    public EqualsNameDateCondition(Event e) {
        this.event = e;
    }
    @Override
    public boolean test(Event data) {
        return this.event.contactIsSchedueled(data);
    }
}
