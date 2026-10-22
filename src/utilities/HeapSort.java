
package utilities;

import java.util.Comparator;
import products.Product;

/**
 * Heap sort implementation for sorting Product arrays in descending order.
 */
public class HeapSort {

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

        int n = arr.length;

        // Build a min-heap from the array.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, comp);
        }

        // Move the smallest product to the end repeatedly.
        for (int end = n - 1; end > 0; end--) {

            // Move the root product to its final position.
            swap(arr, 0, end);

            // Restore the heap using the remaining products.
            heapify(arr, end, 0, comp);
        }
    }

    /**
     * Restores the min-heap property for a subtree.
     *
     * @param arr the array representing the heap
     * @param size the number of products in the heap
     * @param root the root index of the subtree
     * @param comp defines the ordering of the products
     */
    private static void heapify(
            Product[] arr, int size, int root, Comparator<Product> comp) {

        int smallest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        // Find the smaller product between the root and left child.
        if (left < size
                && comp.compare(arr[left], arr[smallest]) < 0) {
            smallest = left;
        }

        // Check whether the right child is smaller.
        if (right < size
                && comp.compare(arr[right], arr[smallest]) < 0) {
            smallest = right;
        }

        // Restore the heap if a child is smaller than the root.
        if (smallest != root) {
            swap(arr, root, smallest);

            // Continue restoring the affected subtree.
            heapify(arr, size, smallest, comp);
        }
    }

    /**
     * Exchanges two products in the array.
     *
     * @param arr the array containing the products
     * @param i the index of the first product
     * @param j the index of the second product
     */
    private static void swap(Product[] arr, int i, int j) {

        // Exchange the two products.
        Product temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
