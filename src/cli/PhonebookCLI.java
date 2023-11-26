package cli;

import Structures.LinkedList;
import models.*;

import java.util.function.Predicate;

/*************Example***************
 CLASS: PhonebookCLI.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/17/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 ***********************************/

public class PhonebookCLI {
    private final Phonebook phonebook;
    private final InputService inputService;

    public PhonebookCLI() {
        this.phonebook = new Phonebook();
        this.inputService = new InputService();
    }

    public void run() {
        System.out.println("Welcome to the BST Phonebook!");
        boolean run = true;
        while (run) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add a contact");
            System.out.println("2. Search for a contact");
            System.out.println("3. Delete a contact");
            System.out.println("4. Schedule an event/appointment");
            System.out.println("5. Print event details");
            System.out.println("6. Print contacts by first name");
            System.out.println("7. Print all events alphabetically");
            System.out.println("8. Exit");
            System.out.println();
            String userInput = inputService.getOption("Enter your choice: ", new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
            switch (userInput) {
                case "1":
                    AddContact();
                    break;
                case "2":
                    SearchContact();
                    break;
                case "3":
                    DeleteContact();
                    break;
                case "4":
                    ScheduleEvent();
                    break;
                case "5":
                    PrintEventDetails();
                    break;
                case "6":
                    PrintContactByFirstName();
                    break;
                case "7":
                    phonebook.displayEvents();
                    break;
                case "8":
                    System.out.println("Goodbye!");
                    run = false;
                    break;
            }
        }
    }

    private void PrintContactByFirstName() {
        String fname = inputService.getString("enter the first name: ");
        Predicate<Contact> cond = contact -> contact.getName().split(" ")[0].equalsIgnoreCase(fname);
        LinkedList<Contact> l = phonebook.filterContacts(cond);
        if (l.empty()) {
            System.out.println("no results :(");
        } else {
            System.out.println("Contacts found!");
            l.display();
        }
    }

    private void PrintEventDetails() {
        System.out.println("Enter search criteria: ");
        System.out.println("1. contact name");
        System.out.println("2. Event title");
        String choice = inputService.getOption("Enter your choice: ", new String[]{"1", "2"});
        if (choice.equals("1")) {
            String name = inputService.getLine("Enter the contact name: ");
            Contact c = phonebook.searchContacts(name);
            if (c != null) {
                c.displayEvents();
            } else {
                System.out.println("no contact found :(");
            }
        }
        if (choice.equals("2")) {
            String title = inputService.getLine("Enter the event title: ");
            Predicate<Event> cond = event -> event.getTitle().equalsIgnoreCase(title);
            LinkedList<Event> l = phonebook.filterEvents(cond);
            if (l.empty()) {
                System.out.println("no results :(");
            } else {
                System.out.println("Events found!");
                l.display();
            }
        }
    }

    private void ScheduleEvent() {
        System.out.println("Enter type:");
        System.out.println("1. event");
        System.out.println("2. appointment");
        String choice = inputService.getOption("Enter your choice: ", new String[]{"1", "2"});
        if (choice.equals("1")) {
            String title = inputService.getLine("Enter event title: ");
            String names = inputService.getLine("Enter contacts name seperated by comma: ");
            String date = inputService.getDate("Enter event date (MM/DD/YYYY): ");
            Time time = inputService.getTime("Enter event time (HH:MM): ");
            String location = inputService.getLine("Enter event location: ");
            try {
                phonebook.addEvent(title, names.split(", "), date, time, location);
                System.out.println("Event added successfully");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (choice.equals("2")) {
            String title = inputService.getLine("Enter event title:");
            String name = inputService.getLine("Enter contact name: ");
            String date = inputService.getDate("Enter event date (MM/DD/YYYY): ");
            Time time = inputService.getTime("Enter event time (HH:MM): ");
            String location = inputService.getLine("Enter event location: ");
            try {
                phonebook.addAppointment(title, name, date, time, location);
                System.out.println("Event added successfully");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void DeleteContact() {
        String name = inputService.getLine("Enter the contact's name: ");
        try {
            phonebook.deleteContact(name);
            System.out.println("Contact deleted!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void SearchContact() {
        System.out.println("Enter search criteria:");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        System.out.println("3. Email Address");
        System.out.println("4. Address");
        System.out.println("5. Birthday");
        String choice = inputService.getOption("Enter your choice: ", new String[]{"1", "2", "3", "4", "5"});
        if (choice.equals("1") || choice.equals("2")) {
            Contact c;
            if (choice.equals("1")) {
                String name = inputService.getLine("Enter the contact's name: ");
                c = phonebook.searchContacts(name);
            } else {
                String number = inputService.getNumber("Enter the contact's number: ");
                Predicate<Contact> cond = contact -> contact.getPhoneNumber().equalsIgnoreCase(number);
                c = phonebook.searchContacts(cond);
            }
            if (c == null) {
                System.out.println("no contact found :(");
            } else {
                System.out.println("Contact found!");
                System.out.println(c);
            }
        } else {
            LinkedList<Contact> l;
            if (choice.equals("3")) {
                String email = inputService.getEmail("Enter the contact's email: ");
                Predicate<Contact> cond = contact -> contact.getEmail().equalsIgnoreCase(email);
                l = phonebook.filterContacts(cond);
            } else if (choice.equals("4")) {
                String address = inputService.getLine("Enter the contact's address: ");
                Predicate<Contact> cond = contact -> contact.getAddress().equalsIgnoreCase(address);
                l = phonebook.filterContacts(cond);
            } else {
                String birthday = inputService.getDate("Enter the contact's birthday: ");
                Predicate<Contact> cond = contact -> contact.getBirthDate().equalsIgnoreCase(birthday);
                l = phonebook.filterContacts(cond);
            }
            if (l.empty()) {
                System.out.println("no contacts found :(");
            } else {
                System.out.println("Contacts found!");
                l.display();
            }
        }
    }

    private void AddContact() {
        String name = inputService.getLine("Enter the contact's name: ");
        String phoneNumber = inputService.getNumber("Enter the contact's phone number: ");
        String email = inputService.getEmail("Enter the contact's email address: ");
        String address = inputService.getLine("Enter the contact's address: ");
        String birthDay = inputService.getDate("Enter the contact's birthday: ");
        String notes = inputService.getLine("Enter any notes for the contact: ");
        Contact c = new Contact(name, phoneNumber, email, address, birthDay, notes);
        try {
            phonebook.addContact(c);
            System.out.println("Contact added successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
