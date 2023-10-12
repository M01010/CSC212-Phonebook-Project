import cli.PhonebookCLI;
import conditions.Condition;
import linkedlist.LinkedList;

public class Main {
    public static void main(String[] args) {
//        PhonebookCLI cli = new PhonebookCLI();
//        cli.fillData();
//        cli.run();
        LinkedList<Integer> l = new LinkedList<>();
        l.add(5);
        l.add(5);
        l.add(5);
        l.add(5);
        l.add(5);
        l.display();
        l.deleteAll(new Condition<Integer>() {
            @Override
            public boolean test(Integer data) {
                return data == 5;
            }
        });
        System.out.println("after");
        l.display();
    }
}