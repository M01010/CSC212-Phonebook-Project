package conditions;

import models.Event;
/*************Example***************
 CLASS: EventHasContact.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/17/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 ***********************************/
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
