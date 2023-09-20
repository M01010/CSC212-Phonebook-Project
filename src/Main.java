import Conditions.Condition;
import Conditions.EqualsCondition;
import LinkedList.LinkedList;

public class Main {
    public static void main(String[] args) {
        PhonebookCLI cli = new PhonebookCLI();
        cli.run();
        LinkedList<Integer> l = new LinkedList<Integer>();
        l.add(3);
        l.add(1);
        l.add(3);
        l.add(5);
        l.add(2);
        l.add(5);
        l.add(-10);
        l.add(30);
        l.add(-4);
        l.display();

        Condition<Integer> equals3 = new EqualsCondition<>(3);
        LinkedList<Integer> filtered = l.searchElements(equals3);
        filtered.display();

        Condition<Integer> equals1 = new EqualsCondition<>(1);
        l.delete(equals1);
        l.display();

    }
}