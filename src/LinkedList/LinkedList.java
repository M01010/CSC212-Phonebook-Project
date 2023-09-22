package LinkedList;

import Conditions.Condition;

public class LinkedList<T extends Comparable<T>> {
    Node<T> head;
    Node<T> current;

    public LinkedList() {
        head = current = null;
    }

    public boolean Empty() {
        return head == null;
    }

    private boolean last() {
        return current.getNext() == null;
    }

    private void insert(T data) {
        Node<T> temp = new Node<>(data);
        if (Empty()) {
            head = current = temp;
        } else {
            temp.setNext(current.getNext());
            current.setNext(temp);
            current = current.getNext();
        }
    }

    private void remove() {
        if (current == head) {
            head = head.getNext();
        } else {
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
        }
        if (Empty()) {
            current = null;
        }
    }

    public void add(T data) {
        if (Empty() || head.getData().compareTo(data) >= 0) { // insert at head or before head
            Node<T> temp = new Node<>(data);
            temp.setNext(head);
            head = current = temp;
        } else {
            current = head;
            while (current.getNext() != null && current.getNext().getData().compareTo(data) <= 0) {
                current = current.getNext();
            }
            insert(data);
        }
    }

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

    public LinkedList<T> filter(Condition<T> condition) {
        LinkedList<T> l = new LinkedList<T>();
        current = head;
        while (current != null) {
            if (condition.test(current.getData())) {
                l.insert(current.getData());
            }
            current = current.getNext();
        }
        return l;
    }

    public void delete(Condition<T> condition) {
        current = head;
        while (current != null) {
            if (condition.test(current.getData())) {
                remove();
                return;
            }
            current = current.getNext();
        }
    }

    public void display() {
        current = head;
        while (current != null) {
            System.out.println(current.getData());
            System.out.println();
            current = current.getNext();
        }
        System.out.println();
    }
}
