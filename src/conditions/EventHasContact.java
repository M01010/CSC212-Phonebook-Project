package conditions;

import models.Event;

public class EventHasContact implements Condition<Event> {

    private final String name;
    /**
     * Test: O(N)
     */
    public EventHasContact(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Event data) {
        return data.contactInEvent(name);
    }

}
