
package utilities;

import java.util.Comparator;

/**
 * Quick sort implementation that sorts in descending order
 * using the median-of-3 approach for pivot selection.
 */
public class QuickSort {

    /**
     * Sorts the array into descending order using the given comparator.
     *
     * @param <E> the element type
     * @param arr the array to sort
     * @param comp defines the ordering of the elements
     */
    public static <E> void sort(E[] arr, Comparator<? super E> comp) {
        if (arr == null || arr.length < 2) {
            return;
        }

        quickSort(arr, 0, arr.length - 1, comp);
    }

    /**
     * Recursively sorts the partitions on either side of the pivot.
     *
     * @param <E> the element type
     * @param arr the array to sort
     * @param low the first index of the partition
     * @param high the last index of the partition
     * @param comp defines the ordering of the elements
     */
    private static <E> void quickSort(
            E[] arr, int low, int high, Comparator<? super E> comp) {

        if (low >= high) {
            return;
        }

        int pivotIndex = partition(arr, low, high, comp);

        quickSort(arr, low, pivotIndex - 1, comp);
        quickSort(arr, pivotIndex + 1, high, comp);
    }

    /**
     * Selects the median of the first, middle, and last elements.
     *
     * @param <E> the element type
     * @param arr the array containing the pivot candidates
     * @param low the first index
     * @param high the last index
     * @param comp defines the ordering of the elements
     * @return the selected median pivot
     */
    private static <E> E medianOfThree(
            E[] arr, int low, int high, Comparator<? super E> comp) {

        int mid = low + (high - low) / 2;

        if (comp.compare(arr[low], arr[mid]) < 0) {
            swap(arr, low, mid);
        }

        if (comp.compare(arr[low], arr[high]) < 0) {
            swap(arr, low, high);
        }

        if (comp.compare(arr[mid], arr[high]) < 0) {
            swap(arr, mid, high);
        }

        E pivot = arr[mid];
        swap(arr, mid, high);

        return pivot;
    }

    /**
     * Partitions the array so elements greater than or equal to
     * the pivot appear before it.
     *
     * @param <E> the element type
     * @param arr the array to partition
     * @param low the first index of the partition
     * @param high the last index of the partition
     * @param comp defines the ordering of the elements
     * @return the final index of the pivot
     */
    private static <E> int partition(
            E[] arr, int low, int high, Comparator<? super E> comp) {

        E pivot = medianOfThree(arr, low, high, comp);
        int i = low;

        for (int j = low; j < high; j++) {
            if (comp.compare(arr[j], pivot) >= 0) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, high);
        return i;
    }

    /**
     * Swaps two elements in the array.
     *
     * @param <E> the element type
     * @param arr the array containing the elements
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
