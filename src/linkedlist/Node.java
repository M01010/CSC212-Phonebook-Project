package linkedlist;
/*************Example***************
 CLASS: Node.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/12/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 ***********************************/
public class Node<T> {
    private final T data;
    private Node<T> next;

    /**
     * O(1)
     */
    public Node(T data) {
        this.data = data;
        next = null;
    }

    /**
     * O(1)
     */
    public T getData() {
        return data;
    }

    /**
     * O(1)
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * O(1)
     */
    public Node<T> getNext() {
        return next;
    }
}
