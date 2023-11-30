package models;

import Structures.BST;
import Structures.LinkedList;

/*************Example***************
 CLASS: Event.java
 CSC212 Data structures - Project phase II
 Fall 2023
 EDIT DATE:
 11/30/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 Faris,    (ID443105725)
 ***********************************/
public class Event implements Comparable<Event> {
    private final String title;
    private final BST<Contact> contacts;
    private final String date;
    private final Time time;
    private final String location;
    private final boolean isAppointment;

    /**
     * O(1)
     */
    public Event(String title, BST<Contact> contacts, String date, Time time, String location, boolean isAppointment) {
        this.title = title;
        this.contacts = contacts;
        this.date = date;
        this.time = time;
        this.location = location;
        this.isAppointment = isAppointment;
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
    public boolean isAppointment() {
        return isAppointment;
    }

    /**
     * O(n)
     * We assume this is O(1) since its a basic operation
     */
    @Override
    public String toString() {
        String s;
        if (isAppointment() || hasOneContact()) {
            s = "Contact: ";
        } else {
            s = "Contacts: ";
        }
        return "Event title: " + title + "\n" + s + getContactNames() + "\n" + "Event date and time (MM/DD/YYYY HH:MM): " + date + " " + time + "\n" + "Event location: " + location;
    }

    /**
     * O(n)
     * returns all contact names in a string
     */
    private String getContactNames() {
        LinkedList<Contact> l = contacts.filter(x -> true);
        l.findFirst();
        String res = "";
        while (!l.last()) {
            res += l.retrieve().getName() + ", ";
            l.findNext();
        }
        res += l.retrieve().getName();
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
     * O(log n)
     */
    public boolean contactInEvent(String name) {
        return contacts.findkey(name);
    }

    /**
     * O(1)
     */
    public boolean hasOneContact() {
        return contacts.hasOneNode();
    }


    /**
     * O(1)
     */
    public boolean conflictsWith(String date, Time time) {
        return this.date.equalsIgnoreCase(date) && this.time.conflictsWith(time);
    }

    /**
     * O(log n)
     */
    public void removeContact(String name) {
        contacts.remove_key(name);
    }
}
