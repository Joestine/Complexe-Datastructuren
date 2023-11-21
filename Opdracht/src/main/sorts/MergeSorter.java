package main.sorts;

import java.util.ArrayList;
import java.util.List;

public class MergeSorter<T extends Comparable<T>> implements Sorter<T> {
    @Override
    public List<T> sort(List<T> list) {
        List<T> sortedList = new ArrayList<>(list);

        if (sortedList.size() < 2) {
            return sortedList;
        }

        int mid = sortedList.size() / 2;
        List<T> leftHalf = new ArrayList<>(sortedList.subList(0, mid));
        List<T> rightHalf = new ArrayList<>(sortedList.subList(mid, sortedList.size()));

        leftHalf = sort(leftHalf);
        rightHalf = sort(rightHalf);

        return merge(leftHalf, rightHalf);
    }

    private List<T> merge(List<T> left, List<T> right) {
        List<T> mergedList = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
                mergedList.add(left.get(leftIndex));
                leftIndex++;
            } else {
                mergedList.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            mergedList.add(left.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < right.size()) {
            mergedList.add(right.get(rightIndex));
            rightIndex++;
        }

        return mergedList;
    }
}