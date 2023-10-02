package models;

import Conditions.Condition;
import Conditions.ContactExists;
import Conditions.EventContactNameOrDateEquals;
import Conditions.EventIsForContact;
import LinkedList.LinkedList;

public class Phonebook {
    final LinkedList<Contact> contacts;
    final LinkedList<Event> events;

    public Phonebook() {
        this.contacts = new LinkedList<Contact>();
        this.events = new LinkedList<Event>();
    }

    /**
     * O(N)
     */
    public boolean addContact(Contact c) {
        ContactExists cond = new ContactExists(c);
        Contact result = contacts.search(cond);
        if (result == null) {
            contacts.add(c);
            return true;
        } else {
            return false;
        }
    }


    public boolean addEvent(Event e) {
        if (e.getContact() == null) {
            return false;
        }
        EventContactNameOrDateEquals cond = new EventContactNameOrDateEquals(e);
        Event result = events.search(cond);
        if (result == null) {
            events.add(e);
            return true;
        } else {
            return false;
        }
    }

    public Contact searchContacts(Condition<Contact> cond) {
        return contacts.search(cond);
    }

    public Event searchEvents(Condition<Event> cond) {
        return events.search(cond);
    }

    public void deleteContact(Condition<Contact> cond) {
        Contact c = contacts.delete(cond);
        EventIsForContact cond2 = new EventIsForContact(c);
        events.delete(cond2);
    }

    public void deleteEvent(Condition<Event> cond) {
        events.delete(cond);
    }

    public LinkedList<Contact> filterContacts(Condition<Contact> cond) {
        return contacts.filter(cond);
    }


    public LinkedList<Event> filterEvents(Condition<Event> cond) {
        return events.filter(cond);
    }

    public void displayContacts() {
        contacts.display();
    }
    public void displayEvents() {
        events.display();
    }
}
