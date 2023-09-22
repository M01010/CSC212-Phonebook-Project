package models;


public class Contact implements Comparable<Contact> {

    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String address;

    private final String birthDate;
    private final String notes;

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public Contact(String name, String phoneNumber, String email, String address, String brithDate, String notes) {
        this.name = name;
        this.address = address;
        this.birthDate = brithDate;
        this.notes = notes;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Contact o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" + "Phone Number: " + this.phoneNumber + "\n" + "Email Address: " + this.email + "\n" + "Address: " + this.address + "\n" + "Birthday: " + this.birthDate + "\n" + "Notes: " + this.notes;
    }
}

