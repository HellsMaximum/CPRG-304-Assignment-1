package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Bubble sort implementation for sorting Product arrays in descending order.
 */
public class BubbleSort {

    /**
     * Sorts the array into descending order using the given comparator.
     *
     * @param arr the array to sort
     * @param comp defines the ordering of the products
     */
    public static void sort(Product[] arr, Comparator<Product> comp) {
        int n = arr.length;

        for (int pass = 0; pass < n - 1; pass++) {
            boolean swapped = false;

            for (int j = 0; j < n - 1 - pass; j++) {
                if (comp.compare(arr[j], arr[j + 1]) < 0) {
                    // The left product is smaller, so swap for descending order.
                    Product temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // Stop early if no swaps were needed during this pass.
            if (!swapped) {
                break;
            }
        }
    }
}