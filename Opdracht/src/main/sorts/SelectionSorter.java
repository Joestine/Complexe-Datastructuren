package main.sorts;

import main.structures.linkedlist.LinkedList;

public class SelectionSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public LinkedList<T> sort(LinkedList<T> list) {
        LinkedList<T> copy = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            copy.add(list.get(i));
        }

        LinkedList<T> sortedList = new LinkedList<>();
        while (!copy.isEmpty()) {
            T smallest = copy.get(0);
            int smallestIndex = 0;
            for (int i = 1; i < copy.size(); i++) {
                if (copy.get(i).compareTo(smallest) < 0) {
                    smallest = copy.get(i);
                    smallestIndex = i;
                }
            }
            sortedList.add(copy.remove(smallestIndex));
        }
        return sortedList;
    }
}
