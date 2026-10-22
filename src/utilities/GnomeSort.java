
package utilities;

import java.util.Comparator;

/**
 * Gnome sort implementation that sorts in descending order.
 */
public class GnomeSort {

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

        int i = 0;

        while (i < arr.length) {

            // Move forward if the adjacent elements are in descending order.
            if (i == 0 || comp.compare(arr[i - 1], arr[i]) >= 0) {
                i++;
            } else {

                // Swap adjacent elements when they are out of order.
                E temp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;

                // Move backward to check the previous pair.
                i--;
            }
        }
    }
}
