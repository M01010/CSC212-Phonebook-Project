import Conditions.*;
import LinkedList.LinkedList;
import models.Contact;
import models.Event;
import models.Phonebook;

public class PhonebookCLI {
    private final Phonebook phonebook;
    private final InputService inputService;

    public PhonebookCLI() {
        this.phonebook = new Phonebook();
        this.inputService = new InputService();
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
            System.out.println("8. Exit");
            System.out.println();
            String userInput = inputService.getString("Enter your choice: ", new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
            if (userInput.equals("1")) {
                addContact();
            } else if (userInput.equals("2")) {
                searchContact();
            } else if (userInput.equals("3")) {
                deleteContact();
            } else if (userInput.equals("4")) {
                scheduleEvent();
            } else if (userInput.equals("5")) {
                printEventDetails();
            } else if (userInput.equals("6")) {
                printContactsByFirstName();
            } else if (userInput.equals("7")) {
                phonebook.displayEvents();
            } else if (userInput.equals("8")) {
                run = false;
            }
        }
    }


    private void addContact() {
        String name = inputService.getString("Enter a name: ");
        String phoneNumber = inputService.getString("Enter a phone number: ");
        String email = inputService.getString("Enter a email: ");
        String address = inputService.getString("Enter an address: ");
        String birthDate = inputService.getString("Enter a birthdate: ");
        String notes = inputService.getString("Enter notes: ");
        Contact c = new Contact(name, phoneNumber, email, address, birthDate, notes);
        boolean added = phonebook.addContact(c);
        if (added) {
            System.out.println("Contact added successfully!");
        } else {
            System.out.println("Cant add Contact, already exists");
        }
    }

    private void searchContact() {
        Contact c = searchForContact();
        if (c == null) {
            System.out.println("no contact found :(");
        } else {
            System.out.println("Contact found!");
            System.out.println(c);
        }
    }

    private void deleteContact() {
        Contact c = searchForContact();
        if (c == null) {
            System.out.println("no contact found :(");
        } else {
            System.out.println("Contact deleted!");
            phonebook.deleteContact(new ContactNameEquals(c.getName()));
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
        Condition<Contact> cond = null;
        if (choice.equals("1")) {
            String name = inputService.getString("Enter the contact's name: ");
            cond = new ContactNameEquals(name);
        } else if (choice.equals("2")) {
            String number = inputService.getString("Enter the contact's number: ");
            cond = new ContactPhoneNumberEquals(number);
        } else if (choice.equals("3")) {
            String email = inputService.getString("Enter the contact's email: ");
            cond = new ContactEmailAddressEquals(email);
        } else if (choice.equals("4")) {
            String address = inputService.getString("Enter the contact's address: ");
            cond = new ContactAddressEquals(address);
        } else if (choice.equals("5")) {
            String birthday = inputService.getString("Enter the contact's birthday: ");
            cond = new ContactBirthdayEquals(birthday);
        }
        return phonebook.searchContacts(cond);
    }


    private void scheduleEvent() {
        String title = inputService.getString("Enter event title:");
        String name = inputService.getString("Enter contact name: ");
        String date = inputService.getString("Enter event date and time (MM/DD/YYYY HH:MM): ");
        String location = inputService.getString("Enter event location: ");
        Contact c = phonebook.searchContacts(new ContactNameEquals(name));
        boolean added = phonebook.addEvent(new Event(title, c, date, location));
        if (added) {
            System.out.println("Event added successfully");
        } else {
            System.out.println("Cant add event, no user with that name");
        }
    }

    private void printEventDetails() {
        System.out.println("Enter search criteria: ");
        System.out.println("1. contact name");
        System.out.println("2. Event title");
        String choice = inputService.getString("Enter your choice: ", new String[]{"1", "2"});
        Condition<Event> cond = null;
        if (choice.equals("1")) {
            String name = inputService.getString("Enter the contact name: ");
            cond = new EventContactNameEquals(name);
        }
        if (choice.equals("2")) {
            String title = inputService.getString("Enter the event title: ");
            cond = new EventTitleEquals(title);
        }
        LinkedList<Event> l = phonebook.filterEvents(cond);
        if (l.Empty()) {
            System.out.println(" no results :(");
        } else {
            System.out.println("Events found!");
            l.display();
        }
    }

    private void printContactsByFirstName() {
        String name = inputService.getString("first name: ");
        ContactFirstNameEquals cond = new ContactFirstNameEquals(name);
        LinkedList<Contact> l = phonebook.filterContacts(cond);
        if (l.Empty()) {
            System.out.println("no results :(");
        } else {
            System.out.println("Contacts found!");
            l.display();
        }
    }

}
