package conditions;

public interface Condition<T> {
    boolean test(T data);
}
