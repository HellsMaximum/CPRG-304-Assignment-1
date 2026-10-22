package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Quick sort implementation for sorting Product arrays in descending order
 * using a median-of-three pivot.
 */
public class QuickSort {

    /**
     * Sorts the array into descending order using the given comparator.
     *
     * @param arr the array to sort
     * @param comp defines the ordering of the products
     */
    public static void sort(Product[] arr, Comparator<Product> comp) {
        // Stop if the array does not need sorting.
        if (arr == null || arr.length < 2) {
            return;
        }

        quickSort(arr, 0, arr.length - 1, comp);
    }

    /**
     * Recursively sorts the sections on either side of the pivot.
     *
     * @param arr the array to sort
     * @param low the starting index of the section
     * @param high the ending index of the section
     * @param comp defines the ordering of the products
     */
    private static void quickSort(
            Product[] arr, int low, int high, Comparator<Product> comp) {

        if (low < high) {
            // Partition the array and find the pivot's final position.
            int pivotIndex = partition(arr, low, high, comp);

            // Sort the sections before and after the pivot.
            quickSort(arr, low, pivotIndex - 1, comp);
            quickSort(arr, pivotIndex + 1, high, comp);
        }
    }

    /**
     * Partitions the array around a median-of-three pivot.
     *
     * @param arr the array to partition
     * @param low the starting index of the section
     * @param high the ending index of the section
     * @param comp defines the ordering of the products
     * @return the final index of the pivot
     */
    private static int partition(
            Product[] arr, int low, int high, Comparator<Product> comp) {

        int mid = low + (high - low) / 2;

        // Order the first, middle, and last products.
        if (comp.compare(arr[low], arr[mid]) < 0) {
            swap(arr, low, mid);
        }

        if (comp.compare(arr[low], arr[high]) < 0) {
            swap(arr, low, high);
        }

        if (comp.compare(arr[mid], arr[high]) < 0) {
            swap(arr, mid, high);
        }

        // Move the median-of-three pivot to the end of the section.
        swap(arr, mid, high);
        Product pivot = arr[high];

        int i = low;

        // Place products greater than or equal to the pivot first.
        for (int j = low; j < high; j++) {
            if (comp.compare(arr[j], pivot) >= 0) {
                swap(arr, i, j);
                i++;
            }
        }

        // Place the pivot in its final position.
        swap(arr, i, high);

        return i;
    }

    /**
     * Exchanges two products in the array.
     *
     * @param arr the array containing the products
     * @param i the index of the first product
     * @param j the index of the second product
     */
    private static void swap(Product[] arr, int i, int j) {
        Product temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}