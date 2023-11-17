package Structures;

import Structures.LinkedList;

import java.util.function.Predicate;

public interface StructureOperations<T extends Comparable<T>> {
    T search(Predicate<T> condition);

    LinkedList<T> filter(Predicate<T> condition);

    T delete(Predicate<T> condition);

    void display();
}
