import Conditions.Condition;
import Conditions.EqualsCondition;
import LinkedList.LinkedList;
import models.Contact;

public class Main {
    public static void main(String[] args) {
        PhonebookCLI cli = new PhonebookCLI();

        cli.run();
        
        Contact C1 = new Contact("Ahmed","","","","","");
         Contact C2 = new Contact("Zyiad","","","","","");
          Contact C3 = new Contact("Ba","","","","","");
       

        LinkedList<Contact> l1 = new LinkedList<>();
        l1.add(C2);
        l1.add(C3);
        l1.add(C1);

        l1.display();

    }
}