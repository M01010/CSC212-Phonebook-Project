package conditions;

import models.Event;

public class EventTitleEquals implements Condition<Event> {

    private final String title;

    public EventTitleEquals(String title) {
        this.title = title;
    }

    @Override
    public boolean test(Event data) {
        return data.getTitle().equalsIgnoreCase(title);
    }

}
