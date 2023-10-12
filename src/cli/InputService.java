package cli;

import java.util.Scanner;

public class InputService {
    private final Scanner input;

    public InputService() {
        input = new Scanner(System.in);
    }

    public String getString(String msg, String[] options) {
        while (true) {
            System.out.print(msg);
            String userInput = input.next();
            for (String option : options) {
                if (userInput.equalsIgnoreCase(option)) {
                    System.out.println();
                    return option;
                }
            }
            System.out.print("options: ");
            for (String option : options) {
                System.out.print(option + " ");
            }
            System.out.println();
        }
    }

    public String getString(String msg) {
        System.out.print(msg);
        return input.next();
    }

    public String getDate(String msg) {
        while (true) {
            String s = getString(msg);
            if (StringValidator.isDate(s)) {
                return s;
            } else {
                System.out.println("Please enter a correct date");
            }
        }
    }

    public String getDateTime(String msg) {
        while (true) {
            System.out.print(msg);
            String s = input.nextLine();
            if (StringValidator.isDateTime(s)) {
                return s;
            } else {
                System.out.println("Please enter a correct date time");
            }
        }
    }

    public String getNumber(String msg) {
        while (true) {
            String s = getString(msg);
            if (StringValidator.isDigit(s)) {
                return s;
            } else {
                System.out.println("Please enter a correct number");
            }
        }
    }

    public String getEmail(String msg) {
        while (true) {
            String s = getString(msg);
            if (StringValidator.isEmail(s)) {
                return s;
            } else {
                System.out.println("Please enter a correct email");
            }
        }
    }

    public String getLine(String msg) {
        System.out.print(msg);
        input.nextLine();
        return input.nextLine();
    }
}
