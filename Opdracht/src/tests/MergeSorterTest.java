package tests;

import main.sorts.MergeSorter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSorterTest {

    private MergeSorter<Integer> sorter;

    @BeforeEach
    public void setUp() {
        sorter = new MergeSorter<>();
    }

    @Test
    public void testEmptyList() {
        List<Integer> list = Collections.emptyList();
        assertTrue(sorter.sort(list).isEmpty());
    }

    @Test
    public void testSingleElement() {
        List<Integer> list = Collections.singletonList(1);
        assertEquals(sorter.sort(list), list);
    }

    @Test
    public void testEvenNumberOfElements() {
        List<Integer> list = Arrays.asList(4, 2, 1, 3);
        assertEquals(sorter.sort(list), Arrays.asList(1, 2, 3, 4));
    }

    @Test
    public void testOddNumberOfElements() {
        List<Integer> list = Arrays.asList(5, 3, 4, 1, 2);
        assertEquals(sorter.sort(list), Arrays.asList(1, 2, 3, 4, 5));
    }

    @Test
    public void testListWithDuplicates() {
        List<Integer> list = Arrays.asList(5, 2, 2, 4, 3);
        assertEquals(sorter.sort(list), Arrays.asList(2, 2, 3, 4, 5));
    }

    @Test
    public void testLargeList() {
        List<Integer> list = Arrays.asList(7623, 7623, 6893, 3218, 4028, 6931, 7622, 9815, 5659, 1641, 9305, 1951, 1721, 950, 4691, 9320, 2873, 6777, 1752, 5808, 3503, 2100, 5466, 5370, 3355, 5918, 2958, 9704, 9996, 9575, 7520);
        assertEquals(sorter.sort(list), Arrays.asList(950, 1641, 1721, 1752, 1951, 2100, 2873, 2958, 3218, 3355, 3503, 4028, 4691, 5370, 5466, 5659, 5808, 5918, 6777, 6893, 6931, 7520, 7622, 7623, 7623, 9305, 9320, 9575, 9704, 9815, 9996));
    }

    @Test
    public void testSortedList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(sorter.sort(list), list);
    }

    @Test
    public void testReverseSortedList() {
        List<Integer> list = Arrays.asList(5, 4, 3, 2, 1);
        assertEquals(sorter.sort(list), Arrays.asList(1, 2, 3, 4, 5));
    }

    @Test
    public void testListWithAllElementsSame() {
        List<Integer> list = Arrays.asList(1, 1, 1, 1);
        assertEquals(sorter.sort(list), list);
    }

    @Test
    public void testNullList() {
        List<Integer> list = null;
        assertThrows(NullPointerException.class, () -> sorter.sort(list));
    }

    @Test
    public void testListWithNulls() {
        List<Integer> list = Arrays.asList(1, 2, null, 4, 5);
        assertThrows(NullPointerException.class, () -> sorter.sort(list));
    }
}
