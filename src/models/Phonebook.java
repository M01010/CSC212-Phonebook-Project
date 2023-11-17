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
     * O(N)
     */
    public boolean addContact(Contact c) {
        Predicate<Contact> cond = contact -> c.getName().equalsIgnoreCase(contact.getName()) || c.getPhoneNumber().equalsIgnoreCase(contact.getPhoneNumber());
        Contact result = contacts.search(cond);
        // didnt find a contact with same name or number
        if (result == null) {
            contacts.insert(c.getName(), c);
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
        Predicate<Event> cond2 = event -> event.getDateTime().equalsIgnoreCase(date) && event.contactInEvent(c.getName());
        Event result = events.search(cond2); // N^2
        // if contact is not schedueled
        if (result == null) {
            // add new event
            LinkedList<Contact> temp_contacts = new LinkedList<>();
            temp_contacts.insert(c);
            Event newEvent = new Event(title, temp_contacts, date, location, false);
            events.add(newEvent);
            return true;
        }
        // contact is busy now
        throw new Exception("Cant add Event, Contact has another event at the same time");
    }


    /**
     * O(N^2)
     */
    public Contact deleteContact(String name) {
        Predicate<Contact> cond = contact -> contact.getName().equalsIgnoreCase(name);
        Contact c = contacts.delete(cond);
        if (c != null) {
            Predicate<Event> cond2 = event -> event.contactInEvent(name);
            events.deleteAll(cond2);
        }
        return c;
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
