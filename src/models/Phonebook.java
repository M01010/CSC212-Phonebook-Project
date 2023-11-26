package models;

import Structures.BST;
import Structures.LinkedList;

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
    private final BST<Contact> contacts;
    private final LinkedList<Event> events;

    /**
     * O(1)
     */
    public Phonebook() {
        this.contacts = new BST<>();
        this.events = new LinkedList<>();
    }


    /**
     * O(n)
     */
    public void addContact(Contact c) throws Exception {
        Predicate<Contact> cond = contact -> contact.getName().equalsIgnoreCase(c.getName());
        Predicate<Contact> cond2 = contact -> contact.getPhoneNumber().equalsIgnoreCase(c.getPhoneNumber());
        Contact result = contacts.search(cond.or(cond2));
        // found a contact with same name or number
        if (result != null) {
            throw new Exception("Can't add Contact, already exists");
        }
        contacts.insert(c.getName(), c);
    }

    /**
     * O(n)
     */
    public void addAppointment(String title, String name, String date, Time time, String location) throws Exception {
        Predicate<Event> cond = event -> event.conflictsWith(date, time);
        Event result = events.search(cond);
        if (result != null) {
            // user has another event at the same time
            throw new Exception("Can't add Event, you have another event at the same time");
        }
        boolean found = contacts.findkey(name);
        if (!found) {
            // No contact with that name
            throw new Exception("Can't add Event, No contact with that name");
        }
        BST<Contact> temp_contacts = new BST<>();
        Event newEvent = new Event(title, temp_contacts, date, time, location, true);
        // add new event
        Contact c = contacts.retrieve();
        temp_contacts.insert(c.getName(), c);
        c.addEvent(newEvent);

        events.add(newEvent);
    }

    /**
     * O(n log n)
     */
    public void addEvent(String title, String[] names, String date, Time time, String location) throws Exception {
        Predicate<Event> cond = event -> event.conflictsWith(date, time);
        Event result = events.search(cond);
        if (result != null) {
            // user has another event at the same time
            throw new Exception("Can't add Event, you have another event at the same time");
        }
        // make sure all contacts exist
        for (int i = 0; i < names.length; i++) {
            boolean found = contacts.findkey(names[i]);
            if (!found) {
                // No contact with that name
                throw new Exception("Can't add Event, No contact called " + names[i]);
            }
        }
        BST<Contact> temp_contacts = new BST<>();
        Event newEvent = new Event(title, temp_contacts, date, time, location, false);
        // add contacts to event
        for (int i = 0; i < names.length; i++) {
            contacts.findkey(names[i]);
            Contact c = contacts.retrieve();
            temp_contacts.insert(c.getName(), c);
            c.addEvent(newEvent);
        }
        events.add(newEvent);
    }


    /**
     * O(n log n)
     */
    public void deleteContact(String name) throws Exception {
        boolean deleted = contacts.remove_key(name);
        if (!deleted) {
            throw new Exception("no contact found :(");
        }
        // remove contact's appointments
        Predicate<Event> cond = event -> event.isAppointment() && event.contactInEvent(name);
        // remove contact's events he is the last one in
        Predicate<Event> cond2 = event -> event.hasOneContact() && event.contactInEvent(name);
        events.deleteAll(cond.or(cond2));

        // remove contact from all events he is in
        if (events.empty()) {
            return;
        }
        events.findFirst();
        while (!events.last()) {
            Event e = events.retrieve();
            if (e.contactInEvent(name)) {
                e.removeContact(name);
            }
            events.findNext();
        }
        Event e = events.retrieve();
        if (e.contactInEvent(name)) {
            e.removeContact(name);
        }
    }

    /**
     * O(N)
     */
    public Contact searchContacts(Predicate<Contact> cond) {
        return contacts.search(cond);
    }

    /**
     * O(logn)
     */
    public Contact searchContacts(String name) {
        boolean found = contacts.findkey(name);
        if (!found) {
            return null;
        }
        return contacts.retrieve();
    }

    /**
     * O(N)
     */
    public LinkedList<Contact> filterContacts(Predicate<Contact> cond) {
        return contacts.filter(cond);
    }

    public LinkedList<Event> filterEvents(Predicate<Event> cond) {
        return events.filter(cond);
    }

    /**
     * O(N)
     */
    public void displayEvents() {
        events.display();
    }
}
