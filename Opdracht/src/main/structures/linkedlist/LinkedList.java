package main.structures.linkedlist;

public class LinkedList<T extends Comparable<T>> {
    private Node head;

    private class Node {
        private final T data;
        private Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        if (head.data.equals(data)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean contains(T data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T linearSearch(T data) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    private Node getMiddle(Node start, Node last) {
        if (start == null)
            return null;

        Node slow = start;
        Node fast = start.next;

        while (fast != last) {
            fast = fast.next;
            if (fast != last) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    public T binarySearch(T data) {
        Node start = head;
        Node last = null;

        Node mid = getMiddle(start, last);

        if (mid == null) {
            return null;
        }
        if (mid.data.compareTo(data) == 0) {
            return mid.data;
        } else if (mid.data.compareTo(data) < 0) {
            start = mid.next;
        } else {
            last = mid;
        }

        while (last == null || last != start) {
            mid = getMiddle(start, last);

            if (mid == null) {
                return null;
            }
            if (mid.data.compareTo(data) == 0) {
                return mid.data;
            } else if (mid.data.compareTo(data) < 0) {
                start = mid.next;
            } else {
                last = mid;
            }
        }

        return null;
    }
}