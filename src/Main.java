import Conditions.EqualsBirthdayCondition;
import Conditions.EqulasNameCondition;
import LinkedList.LinkedList;
import models.Contact;

public class Main {
    public static void main(String[] args) {
        Contact C1 = new Contact("Ahmed", "0565", "A@gmail,com", "g", "3/3", "n");
        Contact C2 = new Contact("Zyiad",   "0563", "Z@gmail.com", "h", "5/2", "n");
        Contact C3 = new Contact("Ba", "057", "B@gmail.com", "f", "9/5", "n");


        LinkedList<Contact> l1 = new LinkedList<Contact>();
        l1.add(C2);
        l1.add(C3);
        l1.add(C1);

        l1.display();
        System.out.println("--------------");

        System.out.println(l1.searchElement(new EqulasNameCondition("Ahmed")));

        l1.delete(new EqualsBirthdayCondition("3/3"));
        System.out.println("--------------");
        l1.display();
    }
}