package Conditions;

import models.Event;

public class EventNameCondition implements Condition<Event> {

    private final String title;

    public EventNameCondition(String title) {
        this.title = title;
    }

    @Override
    public boolean test(Event data) {
        return data.getTitle().equalsIgnoreCase(title);
    }

}
