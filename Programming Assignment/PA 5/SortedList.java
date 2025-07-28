import java.util.ArrayList;
import java.util.Comparator;

/**
 * A generic sorted list that maintains order using binary search insertion.
 * Supports natural ordering or a custom comparator.
 *
 * @param <E> The type of elements in the list, must implement Comparable<E>.
 */
public class SortedList<E extends Comparable<E>> {
    private ArrayList<E> list;
    private Comparator<E> comparator;

    /**
     * Default constructor initializes an empty list with natural ordering.
     * O(1)
     */
    public SortedList() {
        list = new ArrayList<>();
        comparator = null;
    }

    /**
     * Constructor initializes an empty list with a custom comparator.
     *
     * @param c The comparator used for sorting.
     * O(1)
     */
    public SortedList(Comparator<E> c) {
        list = new ArrayList<>();
        comparator = c;
    }

    /**
     * Sets a new comparator for sorting the list and re-sorts the elements.
     *
     * @param c The new comparator to use.
     * O(n log n/n^2)
     */
    public void setComparator(Comparator<E> c) {
        comparator = c;
        sortList();
    }

    /**
     * Adds an element to the sorted list while maintaining order.
     *
     * @param value The element to be added.
     * O(log n), binary is log n and add is O(n)- so overall O(n)
     */
    public void add(E value) {
        if (list.isEmpty()) {
            list.add(value);
            return;
        }
        int insertPos = binarySearchInsert(value);
        list.add(insertPos, value);
    }

    /**
     * Searches for an element in the list using binary search.
     *
     * @param value The element to search for.
     * @return The index of the element if found, otherwise -1.
     * log n
     */
    public int indexOf(E value) {
        return binarySearch(value, 0, list.size() - 1);
    }

    /**
     * Removes an element at a specified index.
     *
     * @param index The index of the element to remove.
     * @throws ArrayIndexOutOfBoundsException If the index is out of bounds.
     * O(n)
     */
    public void remove(int index) {
        if (index < 0 || index >= list.size()) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + list.size());
        }
        list.remove(index);
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return The size of the list.
     * O(1)
     */
    public int size() {
        return list.size();
    }

    /**
     * Retrieves the element at a specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @throws ArrayIndexOutOfBoundsException If the index is out of bounds.
     * O(1)
     */
    public E get(int index) {
        if (index < 0 || index >= list.size()) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + list.size());
        }
        return list.get(index);
    }

    /**
     * Replaces an element at a specified index and maintains sorting order.
     *
     * @param index The index of the element to replace.
     * @param value The new value to insert.
     * @return The old value that was replaced.
     * @throws ArrayIndexOutOfBoundsException If the index is out of bounds.
     * O(1)
     */
    public E set(int index, E value) {
        if (index < 0 || index >= list.size()) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + list.size());
        }
        return list.set(index, value);
        //E old = list.remove(index);
        //add(value);
        //return old;
    }

    /**
     * Returns a string representation of the list with each element on a new line.
     *
     * @return A formatted string of all elements in the list.
     * O(n)
     */
    @Override
    public String toString() {
        String result = "";
        for (E elem : list) {
            result += String.format("%s%n", elem);
        }
        return result;
    }

    /**
     * Sorts the list based on the comparator or natural ordering.
     * either O(n log n)-this actually or O(n^2)-if we assume selection sort, then this
     */
    private void sortList() {
        if (comparator != null) {
            list.sort(comparator);
        } else {
            list.sort(null);
        }
    }

    /**
     * Finds the correct index to insert an element using binary search.
     *
     * @param value The value to insert.
     * @return The index where the element should be inserted.
     * log n
     */
    private int binarySearchInsert(E value) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            E midVal = list.get(mid);
            int cmp = compare(value, midVal);
            if (cmp > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    /**
     * Searches for an element using binary search (recursive method).
     *
     * @param value The value to find.
     * @param low   The lower bound of the search range.
     * @param high  The upper bound of the search range.
     * @return The index of the element if found, otherwise -1.
     * log n
     */
    private int binarySearch(E value, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        E midVal = list.get(mid);
        int cmp = compare(value, midVal);
        if (cmp == 0) {
            return mid;
        } else if (cmp < 0) {
            return binarySearch(value, low, mid - 1);
        } else {
            return binarySearch(value, mid + 1, high);
        }
    }

    /**
     * Compares two elements using the comparator if provided, otherwise uses natural ordering.
     *
     * @param a First element.
     * @param b Second element.
     * @return A negative value if a < b, zero if equal, and a positive value if a > b.
     * O(1)
     */
    private int compare(E a, E b) {
        if (comparator == null) {
            return a.compareTo(b);
        }
        return comparator.compare(a, b);
    }
}
