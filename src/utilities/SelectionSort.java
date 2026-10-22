package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Selection sort implementation for sorting Product arrays in descending order.
 */
public class SelectionSort {

    /**
     * Sorts the array into descending order using the given comparator.
     *
     * @param arr the array to sort
     * @param comp defines the ordering of the products
     */
    public static void sort(Product[] arr, Comparator<Product> comp) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            // Assume the current position contains the largest product.
            int maxIndex = i;

            // Find the largest product in the unsorted section.
            for (int j = i + 1; j < n; j++) {
                if (comp.compare(arr[j], arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }

            // Place the largest product at the current position.
            if (maxIndex != i) {
                Product temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
    }
}