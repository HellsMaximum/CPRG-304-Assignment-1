package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Insertion sort implementation for sorting Product arrays in descending order.
 */
public class InsertionSort {

    /**
     * Sorts the array into descending order using the given comparator.
     *
     * @param arr the array to sort
     * @param comp defines the ordering of the products
     */
    public static void sort(Product[] arr, Comparator<Product> comp) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            // Save the product that needs to be inserted.
            Product current = arr[i];
            int j = i - 1;

            // Shift smaller products to the right.
            while (j >= 0 && comp.compare(arr[j], current) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert the product into its correct position.
            arr[j + 1] = current;
        }
    }
}