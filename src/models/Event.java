package models;

public class Event implements Comparable<Event> {
    private final String title;
    private final Contact contact;
    private final String dateTime;
    private final String location;

    public Event(String title, Contact contact, String dateTime, String location) {
        this.title = title;
        this.contact = contact;
        this.dateTime = dateTime;
        this.location = location;
    }


    @Override
    public String toString() {
        return "Event title: " + title + "\n" + "Contact name: " + contact.getName() + "\n" + "Event date and time (MM/DD/YYYY HH:MM): " + dateTime + "\n" + "Event location: " + location;
    }

    /**
     * O(1)
     */
    @Override
    public int compareTo(Event e) {
        return title.compareTo(e.title);
    }

    /**
     * O(1)
     */
    public boolean contactIsSchedueled(Event e) {
        return contact.getName().equalsIgnoreCase(e.contact.getName()) && dateTime.equalsIgnoreCase(e.dateTime);
    }

    public String getTitle() {
        return title;
    }

    public Contact getContact() {
        return contact;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }
}
