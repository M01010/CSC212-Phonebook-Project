package models;


public class Contact implements Comparable<Contact> {

    public String name;
    public String phoneNumber;
    public String email;
    public String address;
    public String birthDate;
    public String notes;

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

