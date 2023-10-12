import cli.PhonebookCLI;

public class Main {
    public static void main(String[] args) {
        PhonebookCLI cli = new PhonebookCLI();
        cli.fillData();
        cli.run();
    }
}