package Conditions;

import models.Event;

public class EventContactNameEquals implements Condition<Event> {

    private final String name;

    public EventContactNameEquals(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Event data) {
        return data.getContact().getName().equalsIgnoreCase(name);
    }

}
