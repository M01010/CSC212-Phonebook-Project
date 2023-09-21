package Conditions;

import models.Contact;

public class EqualsBirthdayCondition implements Condition<Contact> {
    private String Birthday;

    public EqualsBirthdayCondition(String Birthday){
        this.Birthday = Birthday;
    }

    @Override
    public boolean test(Contact data) {
        return data.birthDate.equalsIgnoreCase(Birthday);
    }
    
}
