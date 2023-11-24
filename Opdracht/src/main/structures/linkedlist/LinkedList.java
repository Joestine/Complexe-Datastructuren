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

    public T remove(int i) {
        if (head == null) {
            return null;
        }
        if (i == 0) {
            T temp = head.getData();
            head = head.getNext();
            return temp;
        }
        Node<T> current = head;
        for (int j = 0; j < i - 1; j++) {
            if (current.getNext() == null) {
                return null;
            }
            current = current.getNext();
        }
        T temp = current.getNext().getData();
        current.setNext(current.getNext().getNext());
        return temp;
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

    public T get(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                return null;
            }
            current = current.getNext();
        }
        return current.getData();
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

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int size = 0;
        Node<T> current = head;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }

    public Node<T> getHead() {
        return head;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(head);
        head = newNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.getData()).append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    public T removeFirst() {
        if (head == null) {
            return null;
        }
        T temp = head.getData();
        head = head.getNext();
        return temp;
    }
}