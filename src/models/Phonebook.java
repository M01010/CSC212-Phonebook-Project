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
        Predicate<Contact> cond = contact -> c.getName().equalsIgnoreCase(contact.getName()) || c.getPhoneNumber().equalsIgnoreCase(contact.getPhoneNumber());
        Contact result = contacts.search(cond);
        // found a contact with same name or number
        if (result != null) {
            throw new Exception("Cant add Contact, already exists");
        }
        contacts.insert(c.getName(), c);
    }

    /**
     * O(n log n)
     */
    public void addEvent(String title, String name, String date, String location, boolean isAppointment) throws Exception {
        boolean found = contacts.findkey(name);
        Contact c = contacts.retrieve();
        // if theres no contact with the same name
        if (!found) {
            throw new Exception("Cant add Event, No contact with that name");
        }
        Predicate<Event> cond2 = event -> event.getDateTime().equalsIgnoreCase(date) && event.contactInEvent(name);
        Event result = events.search(cond2); // nlogn
        if (result != null) {
            // contact is busy now
            throw new Exception("Cant add Event, Contact has another event at the same time");
        }
        // contact is not schedueled
        // add new event
        LinkedList<Contact> temp_contacts = new LinkedList<>();
        if (isAppointment) {
            temp_contacts.insert(c);
            Event newEvent = new Event(title, temp_contacts, date, location, true);
            events.add(newEvent);
        } else {
            temp_contacts.insert(c);
            Event newEvent = new Event(title, temp_contacts, date, location, false);
            events.add(newEvent);
        }
    }


    /**
     * O(n log n)
     */
    public void deleteContact(String name) throws Exception {
        boolean deleted = contacts.remove_key(name);
        if (!deleted) {
            throw new Exception("no contact found :(");
        }
        // remove contacts appointments
        Predicate<Event> cond2 = event -> event.isAppointment() && event.contactInEvent(name);
        events.deleteAll(cond2);

        // remove contact from all events he is in
        Predicate<Event> cond3 = event -> !event.isAppointment() && event.contactInEvent(name);
        LinkedList<Event> l = events.filter(cond3);
        if (l.empty()) {
            return;
        }
        l.findFirst();
        while (!l.last()) {
            Event e = l.retrieve();
            e.removeContact(name);
            l.findNext();
        }
        Event e = l.retrieve();
        e.removeContact(name);
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
    public LinkedList<Contact> filterContacts(Predicate<Contact> cond) {
        return contacts.filter(cond);
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
