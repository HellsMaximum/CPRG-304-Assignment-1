
package utilities;

import java.util.Comparator;

/**
 * Gnome Sort using a supplied comparator.
 * Sorts elements in descending order.
 */
public class GnomeSort {

    public static <T> void sort(T[] arr, Comparator<? super T> comp) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int i = 0;

        while (i < arr.length) {
            if (i == 0 || comp.compare(arr[i - 1], arr[i]) >= 0) {
                i++;
            } else {
                T temp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;

                i--;
            }
        }
    }
}
