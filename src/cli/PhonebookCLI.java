package cli;

import conditions.*;
import linkedlist.LinkedList;
import models.*;

public class PhonebookCLI {
    private final Phonebook phonebook;
    private final InputService inputService;

    public PhonebookCLI() {
        this.phonebook = new Phonebook();
        this.inputService = new InputService();
    }

    public void fillData() {
        Contact c1 = new Contact("mohammed a", "050", "dkfs", "fdsdf", "fsdfds", "dfdsf");
        Contact c2 = new Contact("mohammed alageel", "051", "dkfs", "fdsdf", "fsdfds", "dfdsf");
        Contact c3 = new Contact("ahmed asdk", "052", "dkfs", "fdsdf", "fsdfds", "dfdsf");
        Contact c4 = new Contact("ziyad", "053", "dkfs", "fdsdf", "fsdfds", "dfdsf");

        phonebook.addContact(c1);
        phonebook.addContact(c2);
        phonebook.addContact(c3);
        phonebook.addContact(c4);

        phonebook.addContact(c1); //double

        phonebook.addEvent("event1", c1.getName(), "2020", "rrr");
        phonebook.addEvent("event1", c2.getName(), "2022", "r1r");

        phonebook.addEvent("event2", c3.getName(), "2020", "rrr3");
        phonebook.addEvent("event3", c4.getName(), "2020", "rrr4");

        phonebook.deleteContact(new ContactNameEquals(c3.getName())); // should delete contact and its event

    }

    public void run() {
        boolean run = true;
        while (run) {
            System.out.println();
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
            String userInput = inputService.getString("Enter your choice: ", new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"});
            if (userInput.equals("1")) {
                // add contact
//                inputService.clearBuffer();
                String name = inputService.getLine("Enter a name: ");
                String phoneNumber = inputService.getNumber("Enter a phone number: ");
                String email = inputService.getEmail("Enter a email: ");
                String address = inputService.getLine("Enter an address: ");
                String birthDate = inputService.getDate("Enter a birthdate: ");
                String notes = inputService.getLine("Enter notes: ");
                Contact c = new Contact(name, phoneNumber, email, address, birthDate, notes);
                boolean added = phonebook.addContact(c);
                if (added) {
                    System.out.println("Contact added successfully!");
                } else {
                    System.out.println("Cant add Contact, already exists");
                }
            } else if (userInput.equals("2")) {
                // search for a contact
                Contact c = searchForContact();
                if (c == null) {
                    System.out.println("no contact found :(");
                } else {
                    System.out.println("Contact found!");
                    System.out.println(c);
                }
            } else if (userInput.equals("3")) {
                // delete a contact
                String name = inputService.getLine("Enter the contact's name: ");
                Contact c = phonebook.deleteContact(new ContactNameEquals(name));
                if (c == null) {
                    System.out.println("no contact found :(");
                } else {
                    System.out.println("Contact deleted!");
                }
            } else if (userInput.equals("4")) {
                // Schedule an event
                String title = inputService.getLine("Enter event title:");
                String name = inputService.getLine("Enter contact name: ");
                String date = inputService.getDateTime("Enter event date and time (MM/DD/YYYY HH:MM): ");
                String location = inputService.getLine("Enter event location: ");
                boolean added = phonebook.addEvent(title, name, date, location);
                if (added) {
                    System.out.println("Event added successfully");
                } else {
                    System.out.println("Cant add event, no user with that name");
                }
            } else if (userInput.equals("5")) {
                // print event details
                System.out.println("Enter search criteria: ");
                System.out.println("1. contact name");
                System.out.println("2. Event title");
                String choice = inputService.getString("Enter your choice: ", new String[]{"1", "2"});
                Condition<Event> cond = null;
                if (choice.equals("1")) {
                    String name = inputService.getLine("Enter the contact name: ");
                    cond = new EventContactNameEquals(name);
                }
                if (choice.equals("2")) {
                    String title = inputService.getLine("Enter the event title: ");
                    cond = new EventTitleEquals(title);
                }
                LinkedList<Event> l = phonebook.filterEvents(cond);
                if (l.empty()) {
                    System.out.println("no results :(");
                } else {
                    System.out.println("Events found!");
                    l.display();
                }
            } else if (userInput.equals("6")) {
                // print contacts by first name
                String name = inputService.getString("first name: ");
                ContactFirstNameEquals cond = new ContactFirstNameEquals(name);
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
                Condition<Event> cond = new EventTitleEquals(title);
                Event e = phonebook.searchEvents(cond);
                if (e != null) {
                    LinkedList<Contact> l = e.getContacts();
                    if (l.empty()) {
                        System.out.println("no events added yet :(");
                    } else {
                        l.display();
                    }
                } else {
                    System.out.println("Couldnt find event :(");
                }
            } else if (userInput.equals("9")) {
                // exit
                System.out.println("Goodbye!");
                run = false;
            }
        }
    }

    private Contact searchForContact() {
        System.out.println("Enter search criteria:");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        System.out.println("3. Email Address");
        System.out.println("4. Address");
        System.out.println("5. Birthday");
        String choice = inputService.getString("Enter your choice: ", new String[]{"1", "2", "3", "4", "5"});
//        inputService.clearBuffer();
        Condition<Contact> cond = null;
        if (choice.equals("1")) {
            String name = inputService.getLine("Enter the contact's name: ");
            cond = new ContactNameEquals(name);
        } else if (choice.equals("2")) {
            String number = inputService.getNumber("Enter the contact's number: ");
            cond = new ContactPhoneNumberEquals(number);
        } else if (choice.equals("3")) {
            String email = inputService.getEmail("Enter the contact's email: ");
            cond = new ContactEmailAddressEquals(email);
        } else if (choice.equals("4")) {
            String address = inputService.getLine("Enter the contact's address: ");
            cond = new ContactAddressEquals(address);
        } else if (choice.equals("5")) {
            String birthday = inputService.getDate("Enter the contact's birthday: ");
            cond = new ContactBirthdayEquals(birthday);
        }
        return phonebook.searchContacts(cond);
    }
}
