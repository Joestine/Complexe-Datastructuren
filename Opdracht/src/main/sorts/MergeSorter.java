package main.sorts;

import main.structures.linkedlist.LinkedList;
import main.structures.linkedlist.Node;

public class MergeSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public LinkedList<T> sort(LinkedList<T> list) {
        if (list.isEmpty() || list.size() == 1) {
            return list;
        }

        LinkedList<T> leftHalf = new LinkedList<>();
        LinkedList<T> rightHalf = new LinkedList<>();
        int size = list.size();
        int middle = size / 2;

        Node<T> current = list.getHead();
        int index = 0;
        while (current != null) {
            if (index < middle) {
                leftHalf.add(current.getData());
            } else {
                rightHalf.add(current.getData());
            }
            current = current.getNext();
            index++;
        }

        leftHalf = sort(leftHalf);
        rightHalf = sort(rightHalf);

        return merge(leftHalf, rightHalf);
    }

    private LinkedList<T> merge(LinkedList<T> leftHalf, LinkedList<T> rightHalf) {
        LinkedList<T> merged = new LinkedList<>();
        while (!leftHalf.isEmpty() && !rightHalf.isEmpty()) {
            if (leftHalf.get(0).compareTo(rightHalf.get(0)) <= 0) {
                merged.add(leftHalf.remove(0));
            } else {
                merged.add(rightHalf.remove(0));
            }
        }

        while (!leftHalf.isEmpty()) {
            merged.add(leftHalf.remove(0));
        }
        while (!rightHalf.isEmpty()) {
            merged.add(rightHalf.remove(0));
        }

        return merged;
    }
}
