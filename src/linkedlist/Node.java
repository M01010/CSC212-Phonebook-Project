package linkedlist;

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
