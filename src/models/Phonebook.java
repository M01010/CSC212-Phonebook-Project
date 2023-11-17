package models;

import linkedlist.LinkedList;
import java.util.function.Predicate;
/*************Example***************
 CLASS: Phonebook.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/17/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 ***********************************/
public class Phonebook {
    private final LinkedList<Contact> contacts;
    private final LinkedList<Event> events;

    /**
     * O(1)
     */
    public Phonebook() {
        this.contacts = new LinkedList<>();
        this.events = new LinkedList<>();
    }


    /**
     * O(N)
     */
    public boolean addContact(Contact c) {
        Predicate<Contact> cond = contact -> c.getName().equalsIgnoreCase(contact.getName()) || c.getPhoneNumber().equalsIgnoreCase(contact.getPhoneNumber());
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
    public boolean addEvent(String title, String name, String date, String location) throws Exception {
        Predicate<Contact> cond = contact -> contact.getName().equalsIgnoreCase(name);
        Contact c = searchContacts(cond);
        // if theres no contact with the same name
        if (c == null) {
            throw new Exception("Cant add Event, No contact with that name");
        }
        Predicate<Event> cond2 = event -> event.getTitle().equalsIgnoreCase(title);
        Event e = searchEvents(cond2);
        // if theres an event with the same title
        if (e != null) {
            e.addContact(c);
            return true;
        }
        // add new event
        Predicate<Event> cond3 = event -> event.contactIsSchedueled(date, c);
        Event result = events.search(cond3); // N^2
        // if contact is not schedueled
        if (result == null) {
            Event newEvent = new Event(title, c, date, location);
            events.add(newEvent);
            return true;
        }
        // contact is busy now
        throw new Exception("Cant add Event, Contact has another event at the same time");
    }

    /**
     * O(N)
     */
    public Contact searchContacts(Predicate<Contact> cond) {
        return contacts.search(cond);
    }

    /**
     * O(N)
     */
    public Event searchEvents(Predicate<Event> cond) {
        return events.search(cond);
    }

    /**
     * O(N)
     */
    public Contact deleteContact(Predicate<Contact> cond) {
        Contact c = contacts.delete(cond);
        if (c != null) {
            Predicate<Event> cond2 = event -> event.contactInEvent(c.getName());
            events.deleteAll(cond2);
        }
        return c;
    }

    /**
     * O(N)
     */
    public Event deleteEvent(Predicate<Event> cond) {
        return events.delete(cond);
    }

    /**
     * O(N)
     */
    public LinkedList<Contact> filterContacts(Predicate<Contact> cond) {
        return contacts.filter(cond);
    }


    /**
     * O(N)
     */
    public LinkedList<Event> filterEvents(Predicate<Event> cond) {
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
