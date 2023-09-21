package Conditions;

import models.Contact;

public class EqualsAddressCondition implements Condition<Contact> {
    private String address;
    
    public EqualsAddressCondition(String address){
        this.address = address;
    }

    @Override
    public boolean test(Contact data) {
        return data.address.equalsIgnoreCase(address);
    }
    
    
}
