package Structures;

import java.util.function.Predicate;

/*************Example***************
 CLASS: LinkedList.java
 CSC212 Data structures - Project phase II
 Fall 2023
 EDIT DATE:
 11/26/2023
 TEAM:
 farmers
 AUTHORS:
 Mohammed, (ID443101692)
 Faris,    (ID443105725)
 ***********************************/
public class LinkedList<T extends Comparable<T>> {
    private static class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            next = null;
        }
    }

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
        current = current.next;
    }

    /**
     * O(1)
     */
    public T retrieve() {
        return current.data;
    }

    /**
     * O(1)
     */
    public boolean last() {
        return current.next == null;
    }


    /**
     * O(1)
     */
    public void insert(T data) {
        Node<T> temp = new Node<>(data);
        if (empty()) {
            head = current = temp;
        } else {
            temp.next = current.next;
            current.next = temp;
            current = current.next;
        }
    }

    /**
     * O(N)
     */
    private T remove() {
        if (empty()) {
            return null;
        }
        T res = current.data;
        if (current == head) {
            head = head.next;
            current = head;
            return res;
        }
        Node<T> prev = head;
        while (prev.next != current) {
            prev = prev.next;
        }
        prev.next = current.next;
        if (last()) {
            current = head;
        } else {
            current = current.next;
        }
        return res;
    }

    /**
     * Adds to the list sorted by using compareTo
     * O(N)
     */
    public void add(T data) {
        // insert at head or before head
        if (empty() || head.data.compareTo(data) >= 0) {
            Node<T> temp = new Node<>(data);
            temp.next = head;
            head = current = temp;
            return;
        }
        current = head;
        while (current.next != null && current.next.data.compareTo(data) <= 0) {
            current = current.next;
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
            if (condition.test(temp.data)) {
                return temp.data;
            }
            temp = temp.next;
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
            if (condition.test(temp.data)) {
                l.insert(temp.data);
            }
            temp = temp.next;
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
            if (condition.test(current.data)) {
                return remove();
            }
            current = current.next;
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
        while (head != null && condition.test(head.data)) {
            head = head.next;
        }
        if (empty()) {
            current = null;
            return;
        }
        Node<T> prev = head;
        Node<T> cur = head.next;
        while (cur != null) {
            if (condition.test(cur.data)) {
                // if element matches -> link prev to element after current then move only current
                prev.next = cur.next;
                cur = cur.next;
                continue;
            }
            prev = prev.next;
            cur = cur.next;
        }
    }

    /**
     * Display each element of the list
     * O(N)
     */
    public void display() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            System.out.println();
            temp = temp.next;
        }
        System.out.println();
    }
}
