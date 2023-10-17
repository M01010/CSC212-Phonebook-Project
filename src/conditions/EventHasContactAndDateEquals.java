package conditions;

import models.Contact;
import models.Event;
/*************Example***************
 CLASS: EventHasContactAndDateEquals.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/16/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 Faris,    (ID443105725)
 ***********************************/
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
