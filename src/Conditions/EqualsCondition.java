package Conditions;

public class EqualsCondition<T> implements Condition<T> {
    private final T data;

    public EqualsCondition(T data) {
       this.data = data;
    }

    @Override
    public boolean test(T data) {
        return data.equals(this.data);
    }
}
