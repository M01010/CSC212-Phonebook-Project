package models;

import Structures.BST;
import Structures.LinkedList;

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
    private final BST<Contact> contacts;
    private final String dateTime;
    private final String location;
    private final boolean isAppointment;

    /**
     * O(1)
     */
    public Event(String title, LinkedList<Contact> contacts, String dateTime, String location, boolean isAppointment) {
        this.title = title;

        this.contacts = new BST<>();
        contacts.findFirst();
        while (!contacts.last()) {
            this.contacts.insert(contacts.retrieve().getName(), contacts.retrieve());
        }
        this.contacts.insert(contacts.retrieve().getName(), contacts.retrieve());

        this.dateTime = dateTime;
        this.location = location;
        this.isAppointment = isAppointment;
    }

    /**
     * O(N)
     * We assume this is O(1) since its a basic operation
     */
    @Override
    public String toString() {
        String s;
        if (isAppointment) {
            s = "Contact: ";
        } else {
            s = "Contacts: ";
        }
        return "Event title: " + title + "\n" + s + getContactNames() + "\n" + "Event date and time (MM/DD/YYYY HH:MM): " + dateTime + "\n" + "Event location: " + location;
    }

    /**
     * O(N)
     * returns all contact names in a string
     */
    private String getContactNames() {
        LinkedList<Contact> l = contacts.filter(x -> true);
        l.findFirst();
        String res = "";
        while (!l.last()) {
            res += contacts.retrieve().getName() + " - ";
            l.findNext();
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
    public BST<Contact> getContacts() {
        return contacts;
    }
}
