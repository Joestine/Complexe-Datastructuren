package main.sorts;

import main.structures.linkedlist.LinkedList;

import java.util.List;

public interface Sorter<T extends Comparable<T>> {
    LinkedList<T> sort(LinkedList<T> list);
}
