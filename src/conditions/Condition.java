package conditions;

/*************Example***************
 CLASS: Condition.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/12/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 ***********************************/
public interface Condition<T> {
    boolean test(T data);
}
