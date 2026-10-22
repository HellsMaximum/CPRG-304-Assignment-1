
package utilities;

import java.util.Comparator;

/**
 * Quick Sort using median-of-3 pivot selection.
 * Sorts elements in descending order using the supplied comparator.
 */
public class QuickSort {

    public static <T> void sort(T[] arr, Comparator<? super T> comp) {
        if (arr == null || arr.length < 2) {
            return;
        }

        quickSort(arr, 0, arr.length - 1, comp);
    }

    private static <T> void quickSort(
            T[] arr, int low, int high, Comparator<? super T> comp) {

        if (low >= high) {
            return;
        }

        int pivotIndex = partition(arr, low, high, comp);

        quickSort(arr, low, pivotIndex - 1, comp);
        quickSort(arr, pivotIndex + 1, high, comp);
    }

    // Select the median of the first, middle, and last elements.
    private static <T> T medianOfThree(
            T[] arr, int low, int high, Comparator<? super T> comp) {

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

        // The middle candidate is now the median.
        T pivot = arr[mid];
        swap(arr, mid, high);

        return pivot;
    }

    // Partition the array so larger elements come first.
    private static <T> int partition(
            T[] arr, int low, int high, Comparator<? super T> comp) {

        T pivot = medianOfThree(arr, low, high, comp);
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

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
