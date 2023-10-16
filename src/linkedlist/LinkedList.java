package linkedlist;

import conditions.Condition;

public class LinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> current;

    /**
     * O(1)
     */
    public LinkedList() {
        head = current = null;
    }

    /**
     * O(1)
     */
    public boolean empty() {
        return head == null;
    }

    /**
     * O(1)
     */
    public void findFirst() {
        current = head;
    }

    /**
     * O(1)
     */
    public void findNext() {
        current = current.getNext();
    }

    /**
     * O(1)
     */
    public T retrieve() {
        return current.getData();
    }

    /**
     * O(1)
     */
    public boolean last() {
        return current.getNext() == null;
    }


    /**
     * O(1)
     */
    public void insert(T data) {
        Node<T> temp = new Node<>(data);
        if (empty()) {
            head = current = temp;
        } else {
            temp.setNext(current.getNext());
            current.setNext(temp);
            current = current.getNext();
        }
    }

    public int length() {
        if (empty()) {
            return 0;
        }
        int count = 0;
        Node<T> temp = head;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    /**
     * O(N)
     */
    private T remove() {
        if (empty()) {
            return null;
        }
        T res = current.getData();
        if (current == head) {
            head = head.getNext();
            current = head;
            return res;
        }
        Node<T> prev = head;
        while (prev.getNext() != current) {
            prev = prev.getNext();
        }
        prev.setNext(current.getNext());
        if (last()) {
            current = head;
        } else {
            current = current.getNext();
        }
        return res;
    }

    /**
     * Adds to the list sorted by using compareTo
     * O(N)
     */
    public void add(T data) {
        // insert at head or before head
        if (empty() || head.getData().compareTo(data) >= 0) {
            Node<T> temp = new Node<>(data);
            temp.setNext(head);
            head = current = temp;
            return;
        }
        current = head;
        while (current.getNext() != null && current.getNext().getData().compareTo(data) <= 0) {
            current = current.getNext();
        }
        insert(data);
    }

    /**
     * Searches the list by condition
     * returns 1 matching element
     * if not found: will return null
     * O(N)
     */
    public T search(Condition<T> condition) {
        Node<T> temp = head;
        while (temp != null) {
            if (condition.test(temp.getData())) {
                return temp.getData();
            }
            temp = temp.getNext();
        }
        return null;
    }

    /**
     * Searches the list by condition
     * returns a list of matching elements
     * O(N)
     */
    public LinkedList<T> filter(Condition<T> condition) {
        LinkedList<T> l = new LinkedList<T>();
        Node<T> temp = head;
        while (temp != null) {
            if (condition.test(temp.getData())) {
                l.insert(temp.getData());
            }
            temp = temp.getNext();
        }
        return l;
    }

    /**
     * Searches the list by condition
     * deletes 1 matching element
     * O(N)
     */
    public T delete(Condition<T> condition) {
        current = head;
        while (current != null) {
            if (condition.test(current.getData())) {
                return remove();
            }
            current = current.getNext();
        }
        current = head;
        return null;
    }

    /**
     * Searches the list by condition
     * deletes all matching elements
     * O(N^2)
     */
    public void deleteAll(Condition<T> condition) {
        current = head;
        while (current != null) {
            if (condition.test(current.getData())) {
                if (last()) {
                    remove();
                    break;
                }
                remove();
            } else {
                current = current.getNext();
            }
        }
        current = head;
    }

    /**
     * Display each element of the list
     * O(N)
     */
    public void display() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.println(temp.getData());
            System.out.println();
            temp = temp.getNext();
        }
        System.out.println();
    }
}
