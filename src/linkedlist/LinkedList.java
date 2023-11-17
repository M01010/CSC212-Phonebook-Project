package linkedlist;

import java.util.function.Predicate;

/*************Example***************
 CLASS: LinkedList.java
 CSC212 Data structures - Project phase I
 Fall 2023
 EDIT DATE:
 10/17/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 Faris,    (ID443105725)
 ***********************************/
public class LinkedList<T extends Comparable<T>> implements StructureOperations<T> {
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

    /**
     * O(N)
     */
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
    public T search(Predicate<T> condition) {
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
    public LinkedList<T> filter(Predicate<T> condition) {
        LinkedList<T> l = new LinkedList<>();
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
    public T delete(Predicate<T> condition) {
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
     * O(N)
     */
    public void deleteAll(Predicate<T> condition) {
        // while head matches the condition we delete the element
        while (head != null && condition.test(head.getData())) {
            head = head.getNext();
        }
        if (empty()) {
            current = null;
            return;
        }
        Node<T> prev = head;
        Node<T> cur = head.getNext();
        while (cur != null) {
            if (condition.test(cur.getData())) {
                // if element matches -> link prev to element after current then move only current
                prev.setNext(cur.getNext());
                cur = cur.getNext();
                continue;
            }
            prev = prev.getNext();
            cur = cur.getNext();
        }
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
