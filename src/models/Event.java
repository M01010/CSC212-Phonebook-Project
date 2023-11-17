package models;

import linkedlist.LinkedList;
import java.util.function.Predicate;
/*************Example***************
 CLASS: Event.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/17/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 Faris,    (ID443105725)
 ***********************************/
public class Event implements Comparable<Event> {
    private final String title;
    private final LinkedList<Contact> contacts;
    private final String dateTime;
    private final String location;

    /**
     * O(1)
     */
    public Event(String title, Contact contact, String dateTime, String location) {
        this.title = title;
        this.contacts = new LinkedList<>();
        this.contacts.insert(contact);
        this.dateTime = dateTime;
        this.location = location;
    }

    /**
     * O(N)
     * We assume this is O(1) since its a basic operation
     */
    @Override
    public String toString() {
        String s;
        if (contacts.length() == 1) {
            s = "Contact: ";
        } else {
            s = "Contacts: ";
        }
        return "Event title: " + title + "\n" + s + getContactNames() + "\n" + "Event date and time (MM/DD/YYYY HH:MM): " + dateTime + "\n" + "Event location: " + location;
    }

    /**
     * O(N)
     * returns all contact names in a sting
     */
    private String getContactNames() {
        contacts.findFirst();
        String res = "";
        while (!contacts.last()) {
            res += contacts.retrieve().getName() + " - ";
            contacts.findNext();
        }
        res += contacts.retrieve().getName();
        return res;
    }

    /**
     * O(1)
     */
    @Override
    public int compareTo(Event e) {
        return title.toLowerCase().compareTo(e.title.toLowerCase());
    }

    /**
     * O(N)
     */
    public boolean contactIsSchedueled(String dateTime, Contact c) {
        return this.dateTime.equalsIgnoreCase(dateTime) && contactInEvent(c.getName());
    }

    /**
     * O(N)
     */
    public boolean contactInEvent(String name) {
        Predicate<Contact> cond = contact -> contact.getName().equalsIgnoreCase(name);
        Contact res = contacts.search(cond);
        return res != null;
    }


    /**
     * O(1)
     */
    public String getTitle() {
        return title;
    }

    /**
     * O(1)
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * O(1)
     */
    public String getLocation() {
        return location;
    }

    /**
     * O(1)
     */
    public void addContact(Contact c) {
        contacts.insert(c);
    }

    /**
     * O(1)
     */
    public LinkedList<Contact> getContacts() {
        return contacts;
    }
}
