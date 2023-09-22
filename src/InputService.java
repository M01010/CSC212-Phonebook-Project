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
}
