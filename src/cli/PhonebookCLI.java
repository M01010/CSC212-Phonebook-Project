package cli;

import Structures.BST;
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

    public void fillData() {
        Contact c1 = new Contact("mohammed a", "050", "e1@g.com", "fdsdf", "fsdfds", "dfdsf");
        Contact c2 = new Contact("Mohammed alageel", "051", "e1@g.com", "fdsdf", "fsdfds", "dfdsf");
        Contact c3 = new Contact("ahmed asdk", "052", "dkfs", "fdsdf", "fsdfds", "dfdsf");
        Contact c4 = new Contact("ziyad", "053", "dkfs", "fdsdf", "fsdfds", "dfdsf");
        try {
            phonebook.addContact(c1);
            phonebook.addContact(c2);
            phonebook.addContact(c3);
            phonebook.addContact(c4);
            phonebook.addContact(c1); //double
            phonebook.addEvent("event1", c1.getName(), "2020", "rrr", false);
            phonebook.addEvent("event1", c2.getName(), "2022", "r1r", false);
            phonebook.addEvent("event2", c3.getName(), "2020", "rrr3", false);
            phonebook.addEvent("event3", c4.getName(), "2020", "rrr4", false);
        } catch (Exception ignored) {
        }

        phonebook.displayContacts();
    }

    public void run() {
        System.out.println("Welcome to the Linked Tree Phonebook!");
        boolean run = true;
        while (run) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add a contact");
            System.out.println("2. Search for a contact");
            System.out.println("3. Delete a contact");
            System.out.println("4. Schedule an event");
            System.out.println("5. Print event details");
            System.out.println("6. Print contacts by first name");
            System.out.println("7. Print all events alphabetically");
            System.out.println("8. Print all contacts sharing an event");
            System.out.println("9. Exit");
            System.out.println();
            String userInput = inputService.getOption("Enter your choice: ", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"});
            if (userInput.equals("1")) {
                // add contact
                String name = inputService.getLine("Enter a name: ");
                String phoneNumber = inputService.getNumber("Enter a phone number: ");
                String email = inputService.getEmail("Enter a email: ");
                String address = inputService.getLine("Enter an address: ");
                String birthDate = inputService.getDate("Enter a birthdate: ");
                String notes = inputService.getLine("Enter notes: ");
                Contact c = new Contact(name, phoneNumber, email, address, birthDate, notes);
                try {
                    phonebook.addContact(c);
                    System.out.println("Contact added successfully!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.equals("2")) {
                // search for a contact
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
                        Predicate<Contact> cond = contact -> contact.getName().equalsIgnoreCase(name);
                        c = phonebook.searchContacts(cond);
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
            } else if (userInput.equals("3")) {
                // delete a contact
                String name = inputService.getLine("Enter the contact's name: ");
                try {
                    phonebook.deleteContact(name);
                    System.out.println("Contact deleted!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.equals("4")) {
                // Schedule an event
                String title = inputService.getLine("Enter event title:");
                String name = inputService.getLine("Enter contact name: ");
                String date = inputService.getDateTime("Enter event date and time (MM/DD/YYYY HH:MM): ");
                String location = inputService.getLine("Enter event location: ");
                try {
                    phonebook.addEvent(title, name, date, location, false);
                    System.out.println("Event added successfully");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.equals("5")) {
                // print event details
                System.out.println("Enter search criteria: ");
                System.out.println("1. contact name");
                System.out.println("2. Event title");
                String choice = inputService.getOption("Enter your choice: ", new String[]{"1", "2"});
                Predicate<Event> cond = null;
                if (choice.equals("1")) {
                    String name = inputService.getLine("Enter the contact name: ");
                    cond = event -> event.contactInEvent(name);
                }
                if (choice.equals("2")) {
                    String title = inputService.getLine("Enter the event title: ");
                    cond = event -> event.getTitle().equalsIgnoreCase(title);
                }
                Event e = phonebook.searchEvents(cond);
                if (e == null) {
                    System.out.println("no results :(");
                } else {
                    System.out.println("Event found!");
                    System.out.println(e);
                }
            } else if (userInput.equals("6")) {
                // print contacts by first name
                String fname = inputService.getString("first name: ");
                Predicate<Contact> cond = contact -> contact.getName().split(" ")[0].equalsIgnoreCase(fname);
                LinkedList<Contact> l = phonebook.filterContacts(cond);
                if (l.empty()) {
                    System.out.println("no results :(");
                } else {
                    System.out.println("Contacts found!");
                    l.display();
                }
            } else if (userInput.equals("7")) {
                // print all events alphabetically
                phonebook.displayEvents();
            } else if (userInput.equals("8")) {
                // print all contacts sharing an event
                String title = inputService.getLine("Enter the event title: ");
                Predicate<Event> cond = event -> event.getTitle().equalsIgnoreCase(title);
                Event e = phonebook.searchEvents(cond);
                // no event found
                if (e == null) {
                    System.out.println("Couldnt find event :(");
                } else {
                    BST<Contact> t = e.getContacts();
                    t.display();
                }
            } else if (userInput.equals("9")) {
                // exit
                System.out.println("Goodbye!");
                run = false;
            }
        }
    }
}
