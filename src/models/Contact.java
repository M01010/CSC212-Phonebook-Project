package models;

/*************Example***************
 CLASS: Contact.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/5/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 Musad,    (ID443101092)
 ***********************************/
public class Contact implements Comparable<Contact> {

    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String address;

    private final String birthDate;
    private final String notes;

    /**
     * O(1)
     */
    public String getName() {
        return name;
    }

    /**
     * O(1)
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * O(1)
     */
    public String getEmail() {
        return email;
    }

    /**
     * O(1)
     */
    public String getAddress() {
        return address;
    }

    /**
     * O(1)
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * O(1)
     */
    public String getNotes() {
        return notes;
    }

    /**
     * O(1)
     */
    public Contact(String name, String phoneNumber, String email, String address, String brithDate, String notes) {
        this.name = name;
        this.address = address;
        this.birthDate = brithDate;
        this.notes = notes;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Compares 2 Contact names.
     * O(1)
     */
    @Override
    public int compareTo(Contact o) {
        return this.name.toLowerCase().compareTo(o.name.toLowerCase());
    }

    /**
     * O(1)
     */
    @Override
    public String toString() {
        return "Name: " + this.name + "\n" + "Phone Number: " + this.phoneNumber + "\n" + "Email Address: " + this.email + "\n" + "Address: " + this.address + "\n" + "Birthday: " + this.birthDate + "\n" + "Notes: " + this.notes;
    }
}

