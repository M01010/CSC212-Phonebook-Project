package models;



public class Contact implements Comparable<Contact> {

    public String name;
    public String phoneNumber;
    public String email;
    public String address;
    public String BrithDate;
    public String notes;

    public Contact(String name, String phoneNumber, String email,String address,
    String brithDate,String notes){
        this.name = name;
        this.address=address;
        this.BrithDate = brithDate;
        this.notes = notes;
        this.email=email;
        this.phoneNumber=phoneNumber;

    }

   /*this method is to compare between tow contacts names by alphabetical way so if we have 
   C1 name is Khild, add C2 which name is Ahmed then Ahmed. then the linked will be sorted
   */

    public int compareTo(Contact o) {
        return this.name.compareTo(o.name) ;
        
       
    }
    public String toString(){
        return name;
    }
   


    
}

