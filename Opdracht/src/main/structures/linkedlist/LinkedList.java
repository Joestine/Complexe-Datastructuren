package main.structures.linkedlist;

public class LinkedList<T extends Comparable<T>> {
    private Node<T> head;

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public T remove(T data) {
        if (head == null) {
            return null;
        }
        if (head.getData().equals(data)) {
            T temp = head.getData();
            head = head.getNext();
            return temp;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                T temp = current.getNext().getData();
                current.setNext(current.getNext().getNext());
                return temp;
            }
            current = current.getNext();
        }
        return null;
    }

    public T get(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

}