package models;



public class Contact implements Comparable<Contact> {

    public String Cname;
    public String CphoneNumber;
    public String Cemail;
    public String Caddress;
    public String CBrithDate;
    public String Cnotes;

    public Contact(String name, String phoneNumber, String email,String address,
    String brithDate,String notes){
        this.Cname = name;
        this.Caddress=address;
        this.CBrithDate = brithDate;
        this.Cnotes = notes;
        this.Cemail=email;
        this.CphoneNumber=phoneNumber;

    }

   /*this method is to compare between tow contacts names by alphabetical way so if we have 
   C1 name is Khild, add C2 which name is Ahmed then Ahmed. then the linked will be sorted
   */

    public int compareTo(Contact o) {
        return this.Cname.compareTo(o.Cname) ;
        
       
    }
    public String toString(){
        return Cname;
    }
   


    
}

