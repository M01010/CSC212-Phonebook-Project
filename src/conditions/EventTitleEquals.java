package conditions;

import models.Event;
/*************Example***************
 CLASS: EventTitleEquals.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/12/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 ***********************************/
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
