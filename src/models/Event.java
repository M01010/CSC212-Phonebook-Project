package models;

import Conditions.Condition;
import Conditions.ContactNameEquals;

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
        this.contacts = new LinkedList<Contact>();
        this.contacts.insert(contact);
        this.dateTime = dateTime;
        this.location = location;
    }

    /**
     * O(N)
     */
    @Override
    public String toString() {
        return "Event title: " + title + "\n" + "Contacts: " + getContactNames() + "\n" + "Event date and time (MM/DD/YYYY HH:MM): " + dateTime + "\n" + "Event location: " + location;
    }

    /**
     * O(N)
     */
    private String getContactNames() {
        contacts.findFirst();
        String res = "";
        while (!contacts.last()) {
            res += contacts.retrieve().getName() + " ";
            contacts.findNext();
        }
        res += contacts.retrieve().getName() + " ";
        return res;
    }

    /**
     * O(1)
     */
    @Override
    public int compareTo(Event e) {
        return title.compareTo(e.title);
    }

    /**
     * O(N)
     */
    public boolean contactIsSchedueled(Event e, Contact c) {
        return dateTime.equalsIgnoreCase(e.dateTime) && contactInEvent(c);
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
    public boolean contactInEvent(Contact c) {
        Contact res = contacts.search(new ContactNameEquals(c.getName()));
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
