package cli;

import java.util.Scanner;

public class InputService {
    private final Scanner input;

    public InputService() {
        input = new Scanner(System.in);
    }

    public String getString(String msg) {
        System.out.print(msg);
        String s = input.next();
        input.nextLine(); //clears buffer
        return s;
    }

    public String getLine(String msg) {
        System.out.print(msg);
        return input.nextLine();
    }

    private boolean inOptions(String input, String[] options) {
        for (int i = 0; i < options.length; i++) {
            if (options[i].equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    private void printOptions(String[] options) {
        System.out.print("options: ");
        for (int i = 0; i < options.length; i++) {
            System.out.print(options[i] + " ");
        }
        System.out.println();
    }

    public String getOption(String msg, String[] options) {
        String userInput = getString(msg);
        while (!inOptions(userInput, options)) {
            printOptions(options);
            userInput = getString(msg);
        }
        return userInput;
    }

    public String getDate(String msg) {
        String s = getString(msg);
        while (!StringValidator.isDate(s)) {
            System.out.println("Please enter a correct date");
            s = getString(msg);
        }
        return s;
    }

    public String getDateTime(String msg) {
        String s = getLine(msg);
        while (!StringValidator.isDateTime(s)) {
            System.out.println("Please enter a correct date time (MM/DD/YY HH:MM)");
            s = getLine(msg);
        }
        return s;
    }

    public String getNumber(String msg) {
        String s = getString(msg);
        while (!StringValidator.isDigit(s)) {
            System.out.println("Please enter a correct number");
            s = getString(msg);
        }
        return s;
    }

    public String getEmail(String msg) {
        String s = getString(msg);
        while (!StringValidator.isEmail(s)) {
            System.out.println("Please enter a correct email");
            s = getString(msg);
        }
        return s;
    }
}
