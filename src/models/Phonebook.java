package models;

import conditions.*;
import linkedlist.LinkedList;

public class Phonebook {
    private final LinkedList<Contact> contacts;
    private final LinkedList<Event> events;

    /**
     * O(1)
     */
    public Phonebook() {
        this.contacts = new LinkedList<Contact>();
        this.events = new LinkedList<Event>();
    }
// f
    /**
     * O(N)
     */
    public boolean addContact(Contact c) {
        ContactExists cond = new ContactExists(c);
        Contact result = contacts.search(cond);
        // didnt find a contact with same name or number
        if (result == null) {
            contacts.add(c);
            return true;
        } else {
            return false;
        }
    }

    /**
     * O(N^2)
     */
    public boolean addEvent(String title, String name, String date, String location) {
        Contact c = searchContacts(new ContactNameEquals(name));
        // if theres no contact with the same name
        if (c == null) {
            return false;
        }
        Event e = searchEvents(new EventTitleEquals(title));
        // if theres an event with the same title
        if (e != null) {
            e.addContact(c);
            return true;
        }
        // add new event
        Event newEvent = new Event(title, c, date, location);
        EventHasContactAndDateEquals cond = new EventHasContactAndDateEquals(newEvent, c);
        Event result = events.search(cond); // N^2
        // if contact is not schedueled
        if (result == null) {
            events.add(newEvent);
            return true;
        }
        // contact is busy now
        return false;
    }

    /**
     * O(N)
     */
    public Contact searchContacts(Condition<Contact> cond) {
        return contacts.search(cond);
    }

    /**
     * O(N)
     */
    public Event searchEvents(Condition<Event> cond) {
        return events.search(cond);
    }

    /**
     * O(N^2)
     */
    public Contact deleteContact(Condition<Contact> cond) {
        Contact c = contacts.delete(cond);
        if (c != null) {
            events.deleteAll(new EventHasContact(c));
        }
        return c;
    }

    /**
     * O(N^2) or O(N)
     */
    public Event deleteEvent(Condition<Event> cond) {
        return events.delete(cond);
    }

    /**
     * O(N)
     */
    public LinkedList<Contact> filterContacts(Condition<Contact> cond) {
        return contacts.filter(cond);
    }


    /**
     * O(N)
     */
    public LinkedList<Event> filterEvents(Condition<Event> cond) {
        return events.filter(cond);
    }

    /**
     * O(N)
     */
    public void displayContacts() {
        contacts.display();
    }

    /**
     * O(N)
     */
    public void displayEvents() {
        events.display();
    }
}
