package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Merge sort implementation for sorting Product arrays in descending order.
 */
public class MergeSort {

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

        mergeSort(arr, 0, arr.length - 1, comp);
    }

    /**
     * Recursively divides the array into smaller sections and sorts them.
     *
     * @param arr the array to sort
     * @param left the starting index of the section
     * @param right the ending index of the section
     * @param comp defines the ordering of the products
     */
    private static void mergeSort(
            Product[] arr, int left, int right, Comparator<Product> comp) {

        if (left < right) {
            // Find the middle index of the section.
            int mid = left + (right - left) / 2;

            // Sort the left and right halves.
            mergeSort(arr, left, mid, comp);
            mergeSort(arr, mid + 1, right, comp);

            // Merge the two sorted halves.
            merge(arr, left, mid, right, comp);
        }
    }

    /**
     * Merges two sorted sections into descending order.
     *
     * @param arr the array containing both sections
     * @param left the starting index of the left section
     * @param mid the ending index of the left section
     * @param right the ending index of the right section
     * @param comp defines the ordering of the products
     */
    private static void merge(
            Product[] arr, int left, int mid, int right,
            Comparator<Product> comp) {

        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        // Create temporary arrays for the two sections.
        Product[] leftArray = new Product[leftSize];
        Product[] rightArray = new Product[rightSize];

        // Copy the left section into its temporary array.
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = arr[left + i];
        }

        // Copy the right section into its temporary array.
        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = arr[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        // Select the larger product first to maintain descending order.
        while (i < leftSize && j < rightSize) {
            if (comp.compare(leftArray[i], rightArray[j]) >= 0) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }

            k++;
        }

        // Copy any remaining products from the left section.
        while (i < leftSize) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy any remaining products from the right section.
        while (j < rightSize) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
}