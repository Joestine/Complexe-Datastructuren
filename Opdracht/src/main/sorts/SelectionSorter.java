package main.sorts;

import java.util.ArrayList;
import java.util.List;

public class SelectionSorter<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public List<T> sort(List<T> list) {
        List<T> sortedList = new ArrayList<>(list);

        for (int i = 0; i < sortedList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sortedList.size(); j++) {
                if (sortedList.get(j).compareTo(sortedList.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            T temp = sortedList.get(i);
            sortedList.set(i, sortedList.get(minIndex));
            sortedList.set(minIndex, temp);
        }
        return sortedList;
    }
}
